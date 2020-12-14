/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.CarDAO;
import dao.DAO;
import dao.DAOFactory;
import dao.ReviewDAO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
import model.Car;
import model.Locador;
import model.Pessoa;
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
        name = "CarController",
        urlPatterns = {
            "/car",
            "/car/create",
            "/car/update",
            "/car/delete",
            "/car/read",
            "/fluxo/search",
            "/fluxo/grid",
            "/fluxo/order_by",
            "/fluxo/detail",
            "/fluxo/filterOption",
            "/fluxo/profile",
            "/fluxo/locdetail",
        })
public class CarController extends HttpServlet {

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

        DAO<Car, String> dao;
        DAO<Locador, String> daoL;
        DAO<Pessoa, String> daoP;
        DAO<Review, ArrayList<String>> rdao;
        Car car;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/car": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();

                    List<Car> carList = dao.all();
                    request.setAttribute("carList", carList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/car/index.jsp");
                dispatcher.forward(request, response);
                break;
            }
            
            case "/fluxo/grid": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();
                    

                    List<Car> carList = dao.all();
                    request.setAttribute("carList", carList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/fluxo/grid.jsp");
                dispatcher.forward(request, response);
                break;
            }
            
            case "/fluxo/profile": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();
                    daoL = daoFactory.getLocadorDAO();
                    

                    List<Car> carList = dao.all();
                    List<Locador> locadorList = daoL.all();
                    request.setAttribute("carList", carList);
                    request.setAttribute("locadorList", locadorList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/fluxo/perfil.jsp");
                dispatcher.forward(request, response);
                break;
            }
            
            case "/fluxo/locdetail": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();
                    daoL = daoFactory.getLocadorDAO();
                    daoP = daoFactory.getPessoaDAO();
                    

                    List<Car> carList = dao.all();
                    List<Locador> locadorList = daoL.all();
                    List<Pessoa> pessoaList = daoP.all();
                    request.setAttribute("carList", carList);
                    request.setAttribute("locadorList", locadorList);
                    request.setAttribute("pessoaList", pessoaList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/fluxo/perfilLocador.jsp");
                dispatcher.forward(request, response);
                break;
            }
            
             case "/fluxo/detail": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();
                    rdao = daoFactory.getReviewDAO();
                    
                    List<Car> carList = dao.all();
                    List<Review> reviewList = ((ReviewDAO)rdao).all(request.getParameter("placa"));
                    request.setAttribute("reviewList", reviewList);
                    request.setAttribute("carList", carList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/fluxo/car-detail.jsp");
                dispatcher.forward(request, response);
                break;
             }
             
              case "/fluxo/order_by": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();
                    String order_by = request.getParameter("order_by");

                    List<Car> carList = ((CarDAO)dao).all(order_by);
                    request.setAttribute("carList", carList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/fluxo/grid.jsp");
                dispatcher.forward(request, response);
                break;
            }
              
              case "/fluxo/filterOption": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();
                    String modelo = request.getParameter("modelo_s");
                    int ano = Integer.parseInt(request.getParameter("ano_s"));
                    double preco = Double.parseDouble(request.getParameter("preco_s"));

                    List<Car> carList = ((CarDAO)dao).all(modelo, ano, preco);
                    request.setAttribute("carList", carList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/fluxo/grid.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "/car/create": {
                dispatcher = request.getRequestDispatcher("/view/car/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            
            case "/car/update": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();

                    car = dao.read(request.getParameter("placa"));
                    request.setAttribute("carro", car);

                    dispatcher = request.getRequestDispatcher("/view/car/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/fluxo/profile");
                }
                break;
            }
            
            case "/car/delete": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();
                    dao.delete(request.getParameter("placa"));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/fluxo/profile");
                break;
            }
            
            case "/car/read": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();

                    car = dao.read(request.getParameter("placa"));

                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                    String json = gson.toJson(car);

                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/car");
                }
                break;
            }
            
            case "/fluxo/search": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();

                    
                    List<Car> carList = ((CarDAO)dao).search(request.getParameter("search_by"));
                    request.setAttribute("carList", carList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/fluxo/grid.jsp");
                dispatcher.forward(request, response);
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

        DAO<Car, String> dao;
        Car car = new Car();
        HttpSession session = request.getSession();

        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {
            case "/car/create":
            case "/car/update": {
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
                                case "placa":
                                    car.setPlaca(fieldValue);
                                    break;
                                case "abss":
                                    car.setAbss(Boolean.parseBoolean(fieldValue));
                                    break;
                                case "modelo":
                                    car.setModelo(fieldValue);
                                    break;
                                case "tipo":
                                    car.setTipo(fieldValue);
                                    break;
                                case "ar_condicionado":
                                    car.setAr_condicionado(Boolean.parseBoolean(fieldValue));
                                    break;
                                case "airbags":
                                    car.setAirbags(Boolean.parseBoolean(fieldValue));
                                    break;
                                case "num_lugares":
                                    car.setNum_lugares(Integer.parseInt(fieldValue));
                                    break;
                                case "descricao":
                                    car.setDescricao(fieldValue);
                                    break;
                                case "disponibilidade":
                                    car.setDisponibilidade(Boolean.parseBoolean(fieldValue));
                                    break;
                                case "cpf_locador":
                                    car.setCpf_locador(fieldValue);
                                    break;
                                 case "preco":
                                    car.setPreco(Double.parseDouble(fieldValue));
                                    break;
                                case "ano":
                                    car.setAno(Integer.parseInt(fieldValue));
                                    break;
                                
                            }
                        } else {
                            String fieldName = item.getFieldName();
                            String fileName = item.getName();
                            if (fieldName.equals("avatar") && !fileName.isBlank()) {
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

                                car.setAvatar(fileName);
                            }
                        }
                    }

                    dao = daoFactory.getCarDAO();

                    if (servletPath.equals("/car/create")) {
                        dao.create(car);
                    } else {
                        servletPath += "?placa=" + String.valueOf(car.getPlaca());
                        dao.update(car);
                    }

                    response.sendRedirect(request.getContextPath() + "/fluxo/profile");
                    
                } catch (FileUploadException ex) {
                    Logger.getLogger(CarController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao fazer upload do arquivo.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    Logger.getLogger(CarController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (Exception ex) {
                    Logger.getLogger(CarController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                }
                break;
            }
            
            case "/car/delete": {
                String[] cars = request.getParameterValues("delete");

                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getCarDAO();

                    try {
                        daoFactory.beginTransaction();

                        for (String carPlaca : cars) {
                            dao.delete(carPlaca);
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        session.setAttribute("error", ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(CarController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(CarController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("rollbackError", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/fluxo/profile");
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
