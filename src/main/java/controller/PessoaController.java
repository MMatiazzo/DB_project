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
import dao.PessoaDAO;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Carteira;
import model.Locador;
import model.Locatario;
import model.Pessoa;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author dskaster
 */
@WebServlet(
        name = "PessoaController",
        urlPatterns = {
            "/pessoa",
            "/pessoa/create",
            "/pessoa/update",
            "/pessoa/delete",
            "/pessoa/read",
            "/pessoa/checkLogin"
        })
public class PessoaController extends HttpServlet {

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 4;

    /**
     * Pasta para salvar os arquivos que foram 'upados'. Os arquivos vão ser
     * salvos na pasta de build do servidor. Ao limpar o projeto (clean),
     * pode-se perder estes arquivos. Façam backup antes de limpar.
     */
    private static final String SAVE_DIR = "img";

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

        DAO<Pessoa, String> dao;
        Pessoa pessoa;
        RequestDispatcher dispatcher;

        switch (request.getServletPath()) {
            case "/pessoa": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getPessoaDAO();

                    List<Pessoa> pessoaList = dao.all();
                    request.setAttribute("pessoaList", pessoaList);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/pessoa/index.jsp");
                dispatcher.forward(request, response);
                break;
            }

            case "/pessoa/create": {
                dispatcher = request.getRequestDispatcher("/view/pessoa/create.jsp");
                dispatcher.forward(request, response);
                break;
            }
            
            case "/pessoa/update": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getPessoaDAO();

                    pessoa = dao.read(request.getParameter("cpf"));
                    request.setAttribute("pessoa", pessoa);

                    dispatcher = request.getRequestDispatcher("/view/pessoa/update.jsp");
                    dispatcher.forward(request, response);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/fluxo/profile");
                }
                break;
            }
            
            case "/pessoa/delete": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getPessoaDAO();
                    dao.delete(request.getParameter("cpf"));
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/pessoa");
                break;
            }
            
            case "/pessoa/read": {
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getPessoaDAO();

                    pessoa = dao.read(request.getParameter("cpf"));

                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                    String json = gson.toJson(pessoa);

                    response.getOutputStream().print(json);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/pessoa");
                }
                break;
            }
            
            case "/pessoa/login": {
                
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

        DAO<Pessoa, String> dao;
        DAO<Carteira, String> cdao;
        DAO<Locatario, String> ltdao;
        DAO<Locador, String> lddao;
        Pessoa pessoa = new Pessoa();
        Carteira carteira = new Carteira();
        Locatario locatario = new Locatario();
        Locador locador = new Locador();
                
        HttpSession session = request.getSession();

        String servletPath = request.getServletPath();

        switch (request.getServletPath()) {
            case "/pessoa/create":
            case "/pessoa/update": {
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
                                case "cpf":
                                    pessoa.setCpf(fieldValue);
                                    carteira.setCpf(fieldValue);
                                    carteira.setSaldo(0);
                                    locatario.setCpf_pessoa(fieldValue);
                                    break;
                                case "login":
                                    pessoa.setLogin(fieldValue);
                                    break;
                                case "senha":
                                    pessoa.setSenha(fieldValue);
                                    break;
                                case "nome":
                                    pessoa.setNome(fieldValue);
                                    break;
                                case "nascimento":
                                    java.util.Date dataNascimento = new SimpleDateFormat("yyyy-mm-dd").parse(fieldValue);
                                    pessoa.setNascimento(new Date(dataNascimento.getTime()));
                                    break;
                                //Exclusivo locatario
                                case "endereco":
                                    locatario.setEndereco(fieldValue);
                                    break;
                                case "habilitacao":
                                    locatario.setHabilitacao(fieldValue);
                                    break;
                            }
                        } else {
                            
                            String fieldName = item.getFieldName();
                            String fileName = item.getName();
                            if (!fileName.isBlank()) {
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
                                
                                if(fieldName.equals("avatar")){
                                    pessoa.setAvatar(fileName);
                                } else if(fieldName.equals("comp_renda")){
                                    locatario.setComp_renda(fileName);
                                }
                            }
                            
                        }
                    }

                    dao = daoFactory.getPessoaDAO();
                    cdao = daoFactory.getCarteiraDAO();
                    ltdao = daoFactory.getLocatarioDAO();
                    
                    if (servletPath.equals("/pessoa/create")) {
                        dao.create(pessoa);
                        ltdao.create(locatario);
                        cdao.create(carteira);
                        
                    } else {
                        servletPath += "?cpf=" + String.valueOf(pessoa.getCpf());
                        dao.update(pessoa);
                    }

                    response.sendRedirect(request.getContextPath() + "/fluxo/profile");

                } catch (ParseException ex) {
                    Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "O formato de data não é válido. Por favor entre data no formato dd/mm/aaaa");
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (FileUploadException ex) {
                    Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao fazer upload do arquivo.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + servletPath);
                } catch (Exception ex) {
                    Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", "Erro ao gravar arquivo no servidor.");
                    response.sendRedirect(request.getContextPath() + servletPath);
                }
                break;
            }
            
            case "/pessoa/delete": {
                String[] pessoas = request.getParameterValues("delete");

                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getPessoaDAO();

                    try {
                        daoFactory.beginTransaction();

                        for (String pessoaCpf : pessoas) {
                            dao.delete(pessoaCpf);
                        }

                        daoFactory.commitTransaction();
                        daoFactory.endTransaction();
                    } catch (SQLException ex) {
                        session.setAttribute("error", ex.getMessage());
                        daoFactory.rollbackTransaction();
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("error", ex.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(PessoaController.class.getName()).log(Level.SEVERE, "Controller", ex);
                    session.setAttribute("rollbackError", ex.getMessage());
                }

                response.sendRedirect(request.getContextPath() + "/pessoa");
                break;
            }
            
            
            case "/pessoa/checkLogin": {
                 try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    PessoaDAO udao = daoFactory.getPessoaDAO();

                    pessoa = udao.getByLogin(request.getParameter("login"));

                    Gson gson = new Gson();
                    Map<String, String> result = new HashMap<>();
                    if (pessoa != null) {
                        result.put("status", "USADO");
                    } else {
                        result.put("status", "NAO_USADO");
                    }

                    String json = gson.toJson(result);
                    response.setContentType("application/json");
                    response.getOutputStream().print(json);

                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                    response.sendRedirect(request.getContextPath() + "/pessoa");
                }

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
