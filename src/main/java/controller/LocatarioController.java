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
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.Locatario;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author mathe
 */
@WebServlet(
        name = "LocatarioController",
        urlPatterns = {
            "/locatario",
            "/locatario/create",
            "/locatario/update",
            "/locatario/delete",
            "/locatario/read",
        })
public class LocatarioController extends HttpServlet {
    private static int MAX_FILE_SIZE = 1024 * 1024 * 4;

    /**
     * Pasta para salvar os arquivos que foram 'upados'. Os arquivos vão ser
     * salvos na pasta de build do servidor. Ao limpar o projeto (clean),
     * pode-se perder estes arquivos. Façam backup antes de limpar.
     */
    private static String SAVE_DIR = "img";

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

        DAO<Locatario, String> dao;
        Locatario locatario;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/locatario": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLocatarioDAO();

                    List<Locatario> locatarioList = dao.all();
                    request.setAttribute("locatarioList", locatarioList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/locatario/index.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "/locatario/create": {
                dispatcher = request.getRequestDispatcher("/view/locatario/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            
            case "/locatario/update": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLocatarioDAO();

                    locatario = dao.read(request.getParameter("cpf_pessoa"));
                    request.setAttribute("locatario", locatario);

                    dispatcher = request.getRequestDispatcher("/view/locatario/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/locatario");
                }
                break;
            }
            
            case "/locatario/delete": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLocatarioDAO();
                    dao.delete(request.getParameter("cpf_pessoa"));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/locatario");
                break;
            }
            
            case "/locatario/read": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLocatarioDAO();

                    locatario = dao.read(request.getParameter("cpf_pessoa"));

                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                    String json = gson.toJson(locatario);

                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/locatario");
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

        DAO<Locatario, String> dao;
        Locatario locatario = new Locatario();
        HttpSession session = request.getSession();

        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {
            case "/locatario/create":
            case "/locatario/update": {
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
                                    locatario.setCpf_pessoa(fieldValue);
                                    break;
                                case "habilitacao":
                                    locatario.setHabilitacao(fieldValue);
                                    break;
                                case "endereco":
                                    locatario.setEndereco(fieldValue);
                                    break;
                            }
                        } else {
                            
                            String fieldName = item.getFieldName();
                            String fileName = item.getName();
                            if(fieldName.equals("comp_renda") && !fileName.isBlank()){
                                // Dados adicionais (não usado nesta aplicação)
                                String contentType = item.getContentType();
                                boolean isInMemory = item.isInMemory();
                                long sizeInBytes = item.getSize();

                                // Pega o caminho absoluto da aplicação
                                String appPath = request.getServletContext().getRealPath("");
                                
                                // Grava novo arquivo na pasta img no caminho absoluto
                                String savePath = appPath + File.separator + SAVE_DIR + File.separator + fileName;
                                File uploadedFile = new File(savePath);
                             
                                item.write(uploadedFile);

                                locatario.setComp_renda(fileName);
                            }
                            
                        }
                    }

                    dao = daoFactory.getLocatarioDAO();

                    if (servletPath.equals("/locatario/create")) {
                        dao.create(locatario);
                    } else {
                        servletPath += "?cpf_pessoa=" + String.valueOf(locatario.getCpf_pessoa());
                        dao.update(locatario);
                    }

                    response.sendRedirect(request.getContextPath() + "/locatario");

                } catch (ParseException ex) {
                    Logger.getLogger(LocatarioController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa");
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (FileUploadException ex) {
                    Logger.getLogger(LocatarioController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao fazer upload do arquivo.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    Logger.getLogger(LocatarioController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (Exception ex) {
                    Logger.getLogger(LocatarioController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                }
                break;
            }
            
            case "/locatario/delete": {
                String[] locatarios = request.getParameterValues("delete");

                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getLocatarioDAO();

                    try {
                        daoFactory.beginTransaction();

                        for (String locatarioCpf : locatarios) {
                            dao.delete(locatarioCpf);
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        session.setAttribute("error", ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(LocatarioController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(LocatarioController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("rollbackError", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/locatario");
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
