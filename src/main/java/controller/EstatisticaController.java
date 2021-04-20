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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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
            "/estatistica/montante_gasto_recebido",
            "/estatistica/meses_maior_aluguel",
            "/estatistica/carros_avaliados",
            "/estatistica/quantidade_carros_modelo_ano",
            
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
        String datasets = "";
        
        ArrayList<String> labels;
        ArrayList<Integer> valores;
        ArrayList<Double> precos;
        
        ArrayList<String> nome;
        ArrayList<String> modelo;
        ArrayList<String> placa;
        ArrayList<String> ano;
        ArrayList<String> datasetList;
        
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
                
                
                case "/estatistica/montante_gasto_recebido":
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
                        
//                        Collections.sort(labels, Collections.reverseOrder());

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
                
                case "/estatistica/meses_maior_aluguel":
                    try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getEstatisticaDAO();

                        estatistica = dao.read(4);

                        chart_index_axis = "'x'";
                        chart_type = "'line'";
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
                
                case "/estatistica/carros_avaliados":
                try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                    dao = daoFactory.getEstatisticaDAO();

                    estatistica = dao.read(5);
                    
                    nome = estatistica.getColunas().get(0);
                    modelo = estatistica.getColunas().get(1);
                    ano = estatistica.getColunas().get(2);
                    placa = estatistica.getColunas().get(3);
                    valores = estatistica.getColunas().get(4);
                    
                    
                    request.setAttribute("nomeList", nome);
                    request.setAttribute("modeloList", modelo);
                    request.setAttribute("anoList", ano);
                    request.setAttribute("placaList", placa);
                    request.setAttribute("mediaList", valores);
                } catch (ClassNotFoundException | IOException | SQLException ex) {
                    request.getSession().setAttribute("error", ex.getMessage());
                }

                dispatcher = request.getRequestDispatcher("/view/estatistica/tabela.jsp");
                dispatcher.forward(request, response);
                break;
                
                
                
                 case "/estatistica/quantidade_carros_modelo_ano":
                    try ( DAOFactory daoFactory = DAOFactory.getInstance()) {
                        dao = daoFactory.getEstatisticaDAO();

                        estatistica = dao.read(6);
                        
                        final String[] colors = {"rgb(54, 162, 235)", "rgb(0, 64, 255)", "rgb(255, 0, 64)", "rgb(0, 255, 64)", "rgb(255, 255, 0)"};
                        chart_index_axis = "'x'";
                        chart_type = "'bar'";
                        chart_title = "'Quantidade de carros considerando modelo e ano'";
                        chart_data = "[";
                        chart_labels = "";
                        

                        
                        modelo = estatistica.getColunas().get(0);
                        labels = estatistica.getColunas().get(1);
                        valores = estatistica.getColunas().get(2);
                        
                        
                        //lembrar de tirar daqui do meio
                        Set<String> unique_labels = new HashSet<>(labels);
                        labels = new ArrayList<>(unique_labels);
                        Collections.sort(labels);

                        for(String label : labels){
                            chart_labels += "'" + label + "'" + ",";
                        }
                        
                        chart_labels = chart_labels.substring(0, chart_labels.length() - 1) + "";
                        
                        String atual = "";
                        System.out.println("size:" + modelo.size());
                        for(int i = 0, color_idx = 0; i < modelo.size(); i++){
                            
                            if(!atual.equals(modelo.get(i))){
                                atual = modelo.get(i);
                                
                                datasets += "{ "
                                        + "label: \"" + modelo.get(i) + "\"," 
                                        + "backgroundColor: '" + colors[color_idx++] + "',"
                                        + "borderColor: 'rgb(255, 55, 132)',"
                                        + "data: [";
                                
                                while(atual.equals(modelo.get(i))){
                                    datasets += valores.get(i) + ",";  
                                    i++;
                                    if(i >= modelo.size())
                                        break;
                                }
                                datasets = datasets.substring(0, datasets.length() - 1) + "]},";
                                i--;
                            }
                        }
                        
                        datasets = datasets.substring(0, datasets.length() - 1) + "";

//                        for(String label : labels){
//                            chart_labels += "'" + label + "'" + ",";
//                        }


                        request.setAttribute("chartType", chart_type);
                        request.setAttribute("chartTitle", chart_title);
//                        request.setAttribute("chartData", chart_data);
                        request.setAttribute("chartLabels", chart_labels);
                        request.setAttribute("chartIndexAxis", chart_index_axis);
                        request.setAttribute("datasets", datasets);

                    } catch (ClassNotFoundException | IOException | SQLException ex) {
                        request.getSession().setAttribute("error", ex.getMessage());
                    }

                    dispatcher = request.getRequestDispatcher("/view/estatistica/multi_dataset.jsp");
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
