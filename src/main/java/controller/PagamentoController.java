/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dao.DAOFactory;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pagamento;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author mathe
 */
@WebServlet(
        name = "PagamentoController",
        urlPatterns = {
            "/pagamento",
            "/pagamento/create",
            "/pagamento/update",
            "/pagamento/delete",
            "/pagamento/read",
            "/pagamento/checkLogin"
        })
public class PagamentoController extends HttpServlet {
     private static int MAX_FILE_SIZE = 1024 * 1024 * 4;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DAO<Pagamento, ArrayList<String>> dao;
        Pagamento pagamento;
        ArrayList<String> pagamentos;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/pagamento": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getPagamentoDAO();

                    List<Pagamento> pagamentoList = dao.all();
                    request.setAttribute("pagamentoList", pagamentoList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/pagamento/index.jsp");
                dispatcher.forward(request, response);
                break;
            }
            
            case "/pagamento/create": {
                dispatcher = request.getRequestDispatcher("/view/pagamento/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            
             case "/pagamento/delete": {
                 pagamentos = new ArrayList<String>();
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getPagamentoDAO();
                    pagamentos.add(request.getParameter("data_pagamento"));
                    pagamentos.add(request.getParameter("num_placa_carro"));
                    pagamentos.add(request.getParameter("cpf_locador"));
                    pagamentos.add(request.getParameter("cpf_locatario"));
                    dao.delete(pagamentos);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/pagamento");
                break;
            }
             
             case "/pagamento/update": {
                 pagamentos = new ArrayList<String>();
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getPagamentoDAO();
                    pagamentos.add(request.getParameter("data_pagamento"));
                    pagamentos.add(request.getParameter("num_placa_carro"));
                    pagamentos.add(request.getParameter("cpf_locador"));
                    pagamentos.add(request.getParameter("cpf_locatario"));
//                    pagamentos.add(request.getParameter("data_entrega"));
//                    pagamentos.add(request.getParameter("valor"));
//                    pagamentos.add(request.getParameter("data_devolucao"));
                    pagamento = dao.read(pagamentos);
                    request.setAttribute("pagamento", pagamento);

                    dispatcher = request.getRequestDispatcher("/view/pagamento/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/pagamento");
                }
                break;
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO<Pagamento, ArrayList<String> >dao;
        ArrayList<String> pagamentos;
        Pagamento pagamento = new Pagamento();
        HttpSession session = request.getSession();

        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {
            
            case "/pagamento/create":
            case "/pagamento/update": {
                // Se fosse um form simples, usaria request.getParameter()
                // String login = request.getParameter("login");

                // Manipulação de form com enctype="multipart/form-data"
                // Create a factory for disk-based file items
                DiskFileItemFactory factory = new DiskFileItemFactory();
                // Set factory constraints
                factory.setSizeThreshold(MAX_FILE_SIZE);
                // Set the directory used to temporarily store files that are larger than the configured size threshold
                factory.setRepository(new File("/tmp"));
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // Set overall request size constraint
                upload.setSizeMax(MAX_FILE_SIZE);

                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    // Parse the request
                    List<FileItem> items = upload.parseRequest(request);

                    // Process the uploaded items
                    Iterator<FileItem> iter = items.iterator();
                    while (iter.hasNext()) {
                        FileItem item = iter.next();

                        // Process a regular form field
                        if (item.isFormField()) {
                            String fieldName = item.getFieldName();
                            String fieldValue = item.getString();

                            switch (fieldName) {
                                case "data_pagamento":
                                    java.util.Date dataPagamento = new SimpleDateFormat("yyyy-mm-dd").parse(fieldValue);
                                    pagamento.setData_pagamento(new Date(dataPagamento.getTime()));
                                    break;
                                case "num_placa_carro":
                                    pagamento.setNum_placa_carro(fieldValue);
                                    break;
                                case "cpf_locador":
                                    pagamento.setCpf_locador(fieldValue);
                                    break;
                                case "cpf_locatario":
                                    pagamento.setCpf_locatario(fieldValue);
                                    break;
                                case "valor":
                                    pagamento.setValor(Integer.parseInt(fieldValue));
                                    break;
                                case "data_entrega":
                                    java.util.Date dataEntrega = new SimpleDateFormat("yyyy-mm-dd").parse(fieldValue);
                                    pagamento.setData_entrega(new Date(dataEntrega.getTime()));
                                    break;
                                case "data_devolucao":
                                    java.util.Date dataDevolucao = new SimpleDateFormat("yyyy-mm-dd").parse(fieldValue);
                                    pagamento.setData_devolucao(new Date(dataDevolucao.getTime()));
                                    break;
                            }
                        }
                         
                    }

                    dao = daoFactory.getPagamentoDAO();

                    if (servletPath.equals("/pagamento/create")) {
                        dao.create(pagamento);
                    } else {
                        servletPath += "?data_pagamento=" + String.valueOf(pagamento.getData_pagamento() + "&num_placa_carro=" + String.valueOf(pagamento.getNum_placa_carro()) + "&cpf_locador=" + String.valueOf(pagamento.getCpf_locador()) + "&cpf_locatario= " + String.valueOf(pagamento.getCpf_locatario()));
                        dao.update(pagamento);
                    }

                    response.sendRedirect(request.getContextPath() + "/pagamento");

                } catch (ParseException ex) {
                    Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa");
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (FileUploadException ex) {
                    Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao fazer upload do arquivo.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (Exception ex) {
                    Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                }
                break;
            }
            
            case "/pagamento/delete": {
//                     String[] pagamento = request.getParameterValues("delete");
                     pagamentos = new ArrayList<String>();
                     String[] pagamentos_string = request.getParameterValues("delete");
                     for(int i = 0; i < pagamentos_string.length ;i++){
                         pagamentos.add(i,  pagamentos_string[i]);
                     }
//                     pagamentos = request.getParameterValues("delete");
                     try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                         dao = daoFactory.getPagamentoDAO();

                         try {
                             daoFactory.beginTransaction();

//                             for (String pessoaCpf : pessoas) {
//                                 dao.delete(pessoaCpf);
//                             }

                             daoFactory.commitTransaction();
                             daoFactory.endTransaction();
                         } catch (SQLException ex) {
                             session.setAttribute("error", ex.getMessage());
                             daoFactory.rollbackTransaction();
                         }
                     } catch (ClassNotFoundException | IOException ex) {
                         Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, "Controller", ex);
                         session.setAttribute("error", ex.getMessage());
                     } catch (SQLException ex) {
                         Logger.getLogger(PagamentoController.class.getName()).log(Level.SEVERE, "Controller", ex);
                         session.setAttribute("rollbackError", ex.getMessage());
                     }

                     response.sendRedirect(request.getContextPath() + "/pagamento");
                     break;
                 }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
