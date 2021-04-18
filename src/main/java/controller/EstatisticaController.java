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
import java.util.ArrayList;
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
            "/estatistica/carros_por_pessoa",
            "/estatistica/media_preco_por_modelo",
            "/estatistica/montate_gasto_recebido",
            "/estatistica/meses__maior_aluguel"
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
        
        String chart_data;
        String chart_labels;
        String chart_type;
        String chart_title;
        String chart_index_axis;
        
        ArrayList<String> labels;
        ArrayList<Integer> valores;
        ArrayList<Double> precos;
        
        
        switch (request.getServletPath()) {
            case "/estatistica/carros_por_pessoa":
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getEstatisticaDAO();

                    estatistica = dao.read(1);
                    chart_index_axis = "'x'";
                    chart_title = "'Carros por Locador'";
                    chart_type = "'bar'";
                    chart_labels = "";
                    chart_data = "[";
                    
                    labels = estatistica.getColunas().get(0);
                    valores = estatistica.getColunas().get(1);
                    
                    for(Integer integer : valores){
                        chart_data += integer + ",";
                    }
                    
                    chart_data = chart_data.substring(0, chart_data.length() - 1) + "]";
                    
                    for(String label : labels){
                        chart_labels += "'" + label + "'" + ",";
                    }
                    
                    
                    request.setAttribute("chartType", chart_type);
                    request.setAttribute("chartTitle", chart_title);
                    request.setAttribute("chartData", chart_data);
                    request.setAttribute("chartLabels", chart_labels);
                    request.setAttribute("chartIndexAxis", chart_index_axis);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/estatistica/index.jsp");
                dispatcher.forward(request, response);
                break;
                
            case "/estatistica/media_preco_por_modelo":
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getEstatisticaDAO();

                    estatistica = dao.read(2);
                    
                    chart_index_axis = "'x'";
                    chart_type = "'bar'";
                    chart_title = "'Media de preços por modelo de carro'";
                    chart_data = "[";
                    chart_labels = "";
                    
                    labels = estatistica.getColunas().get(0);
                    precos = estatistica.getColunas().get(1);
                    
                    for(Double preco : precos){
                        chart_data += preco + ",";
                    }
                    chart_data = chart_data.substring(0, chart_data.length() - 1) + "]";
                    
                    for(String label : labels){
                        chart_labels += "'" + label + "'" + ",";
                    }
                    
                    
                    request.setAttribute("chartType", chart_type);
                    request.setAttribute("chartTitle", chart_title);
                    request.setAttribute("chartData", chart_data);
                    request.setAttribute("chartLabels", chart_labels);
                    request.setAttribute("chartIndexAxis", chart_index_axis);
                    
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/estatistica/index.jsp");
                dispatcher.forward(request, response);
                break;
                
                
                case "/estatistica/montate_gasto_recebido":
                    try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getEstatisticaDAO();

                        estatistica = dao.read(3);

                        chart_index_axis = "'y'";
                        chart_type = "'bar'";
                        chart_title = "'Montante Gasto ou recebido por pessoa'";
                        chart_data = "[";
                        chart_labels = "";
                        
                        labels = estatistica.getColunas().get(0);
                        precos = estatistica.getColunas().get(1);

                        for(Double preco : precos){
                            chart_data += preco + ",";
                        }
                        chart_data = chart_data.substring(0, chart_data.length() - 1) + "]";

                        for(String label : labels){
                            chart_labels += "'" + label + "'" + ",";
                        }


                        request.setAttribute("chartType", chart_type);
                        request.setAttribute("chartTitle", chart_title);
                        request.setAttribute("chartData", chart_data);
                        request.setAttribute("chartLabels", chart_labels);
                        request.setAttribute("chartIndexAxis", chart_index_axis);

                    } catch (ClassNotFoundException | IOException | SQLException ex) {
                        request.getSession().setAttribute("error", ex.getMessage());
                    }

                    dispatcher = request.getRequestDispatcher("/view/estatistica/index.jsp");
                    dispatcher.forward(request, response);
                    break;
                
                case "/estatistica/meses__maior_aluguel":
                    try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getEstatisticaDAO();

                        estatistica = dao.read(4);

                        chart_index_axis = "'x'";
                        chart_type = "'doughnut'";
                        chart_title = "'Carros Adicionados Mensamente'";
                        chart_data = "[";
                        chart_labels = "";

                        
                        labels = estatistica.getColunas().get(0);
                        valores = estatistica.getColunas().get(1);
                        
                        for(Integer integer : valores){
                            chart_data += integer + ",";
                        }
                        
                        chart_data = chart_data.substring(0, chart_data.length() - 1) + "]";

                        for(String label : labels){
                            chart_labels += "'" + label + "'" + ",";
                        }


                        request.setAttribute("chartType", chart_type);
                        request.setAttribute("chartTitle", chart_title);
                        request.setAttribute("chartData", chart_data);
                        request.setAttribute("chartLabels", chart_labels);
                        request.setAttribute("chartIndexAxis", chart_index_axis);

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
