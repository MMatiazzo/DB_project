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
import dao.ReviewDAO;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
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
import model.Review;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Guilherme
 */
@WebServlet(
        name = "ReviewController",
        urlPatterns = {
            "/review",
            "/review/create",
            "/review/update",
            "/review/delete",
            "/review/read",
            "/fluxo/car_details"
        })
public class ReviewController extends HttpServlet {

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

        DAO<Review, ArrayList<String>> dao;
        ArrayList<String> keys;
        Review review;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/review": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getReviewDAO();

                    List<Review> reviewList = dao.all();
                    request.setAttribute("reviewList", reviewList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/review/index.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "/review/create": {
                dispatcher = request.getRequestDispatcher("/view/review/create.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "/review/update": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    keys = new ArrayList<>();
                    dao = daoFactory.getReviewDAO();

                    keys.add(request.getParameter("num_placa_carro"));
                    keys.add(request.getParameter("cpf_locador"));
                    keys.add(request.getParameter("cpf_locatario"));
                    review = dao.read(keys);
                    request.setAttribute("review", review);

                    dispatcher = request.getRequestDispatcher("/view/review/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/review");
                }
                break;
            }

            case "/review/delete": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getReviewDAO();
                    keys = new ArrayList<>();
                    keys.add(request.getParameter("num_placa_carro"));
                    keys.add(request.getParameter("cpf_locador"));
                    keys.add(request.getParameter("cpf_locatario"));
                    dao.delete(keys);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/review");
                break;
            }

            case "/review/read": {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getReviewDAO();
                    keys = new ArrayList<>();
                    keys.add(request.getParameter("num_placa_carro"));
                    keys.add(request.getParameter("cpf_locador"));
                    keys.add(request.getParameter("cpf_locatario"));
                    review = dao.read(keys);

                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                    String json = gson.toJson(review);

                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/review");
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

        DAO<Review, ArrayList<String>> dao;
        Review review = new Review();
        HttpSession session = request.getSession();

        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {
            case "/review/create":
            case "/review/update": {
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

                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
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
                                case "num_placa_carro":
                                    review.setNum_placa_carro(fieldValue);
                                    break;
                                case "cpf_locador":
                                    review.setCpf_locador(fieldValue);
                                    break;
                                case "cpf_locatario":
                                    review.setCpf_locatario(fieldValue);
                                    break;
                                case "descricao":
                                    review.setDescricao(fieldValue);
                                    break;
                                case "nota":
                                    review.setNota(Integer.parseInt(fieldValue));
                                    break;
                                case "data_review":
                                    java.util.Date dataReview = new SimpleDateFormat("yyyy-mm-dd").parse(fieldValue);
                                    review.setData_review(new Date(dataReview.getTime()));
                                    break;
                            }
                        }
                    }

                    dao = daoFactory.getReviewDAO();

                    if (servletPath.equals("/review/create")) {
                        dao.create(review);
                    } else {
                        servletPath += "?num_placa_carro=" + review.getNum_placa_carro() + "&cpf_locador="
                                + review.getCpf_locador() + "&cpf_locatario=" + review.getCpf_locatario();
                        dao.update(review);
                    }

                    response.sendRedirect(request.getContextPath() + "/review");

                } catch (ParseException ex) {
                    Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa");
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (FileUploadException ex) {
                    Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao fazer upload do arquivo.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (Exception ex) {
                    Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                }
                break;
            }

//            IMPLEMENTAR DELETAR VARIOS
            case "/review/delete": {
                String[] reviews = request.getParameterValues("delete");
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getReviewDAO();

                    try {
                        daoFactory.beginTransaction();

                        for (String userId : reviews) {
//                            dao.delete(Integer.parseInt(userId));
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        session.setAttribute("error", ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("rollbackError", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/review");
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
