/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAO;
import dao.DAOFactory;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Estatistica;

/**
 *
 * @author Guilherme
 */
@WebServlet(
        name = "EstatisticaController", 
        urlPatterns = {
            "/estatistica/carros_por_pessoa"
        })
public class EstatisticaController extends HttpServlet {

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
        
        DAO<Estatistica, Integer> dao;
        Estatistica estatistica;
        
        RequestDispatcher dispatcher;
        
        String data;
        
        
        switch (request.getServletPath()) {
            case "/estatistica/carros_por_pessoa":
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getEstatisticaDAO();

                    estatistica = dao.read(1);
                    
                    data = "[";
                    for(Integer integer : estatistica.getValores()){
                        data += integer + ",";
                    }
                    data = data.substring(0, data.length() - 1) + "]";
                    
                    
                    request.setAttribute("chartData", data);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/estatistica/index.jsp");
                dispatcher.forward(request, response);
                break;
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
        RequestDispatcher dispatcher;
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
