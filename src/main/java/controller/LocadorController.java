/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DAO;
import dao.DAOFactory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
import model.Locador;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *
 * @author Guilherme
 */
@WebServlet(
        name = "LocadorController",
        urlPatterns = {
            "/locador",
            "/locador/create",
            "/locador/update",
            "/locador/delete",
            "/locador/read"
        })
public class LocadorController extends HttpServlet {

    private static int MAX_FILE_SIZE = 1024 * 1024 * 4;

    /**
     * Pasta para salvar os arquivos que foram 'upados'. Os arquivos vão ser
     * salvos na pasta de build do servidor. Ao limpar o projeto (clean),
     * pode-se perder estes arquivos. Façam backup antes de limpar.
     */
    private static String SAVE_DIR = "img";

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

        DAO<Locador, String> dao;
        Locador locador;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/locador": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLocadorDAO();

                    List<Locador> locadorList = dao.all();
                    request.setAttribute("locadorList", locadorList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/locador/index.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "/locador/create": {
                dispatcher = request.getRequestDispatcher("/view/locador/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            
            case "/locador/update": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLocadorDAO();

                    locador = dao.read(request.getParameter("cpf_pessoa"));
                    request.setAttribute("locador", locador);

                    dispatcher = request.getRequestDispatcher("/view/locador/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/locador");
                }
                break;
            }
            
            case "/locador/delete": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLocadorDAO();
                    dao.delete(request.getParameter("cpf_pessoa"));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/locador");
                break;
            }
            
            case "/locador/read": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLocadorDAO();

                    locador = dao.read(request.getParameter("cpf_pessoa"));

                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                    String json = gson.toJson(locador);

                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/locador");
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

        DAO<Locador, String> dao;
        Locador locador = new Locador();
        HttpSession session = request.getSession();

        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {
            case "/locador/create":
            case "/locador/update": {
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
                                case "cpf_pessoa":
                                    locador.setCpf_pessoa(fieldValue);
                                    break;
                                case "doc_carro":
                                    locador.setDoc_carro(fieldValue);
                                    break;
                            }
                        }
                    }

                    dao = daoFactory.getLocadorDAO();

                    if (servletPath.equals("/locador/create")) {
                        dao.create(locador);
                    } else {
                        servletPath += "?cpf_pessoa=" + String.valueOf(locador.getCpf_pessoa());
                        dao.update(locador);
                    }

                    response.sendRedirect(request.getContextPath() + "/locador");
                    
                } catch (FileUploadException ex) {
                    Logger.getLogger(LocadorController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao fazer upload do arquivo.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    Logger.getLogger(LocadorController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (Exception ex) {
                    Logger.getLogger(LocadorController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                }
                break;
            }
            
            case "/locador/delete": {
                String[] locadores = request.getParameterValues("delete");

                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLocadorDAO();

                    try {
                        daoFactory.beginTransaction();

                        for (String locadorCpf : locadores) {
                            dao.delete(locadorCpf);
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        session.setAttribute("error", ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(LocadorController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(LocadorController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("rollbackError", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/locador");
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

