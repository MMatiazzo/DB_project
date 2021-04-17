/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Estatistica;

/**
 *
 * @author Guilherme
 */
public class PgEstatisticaDAO implements EstatisticaDAO{
    
    private final Connection connection;
    
    private static final String READ_QUERY_CARS_PER_PERSON
            = "SELECT nome,SUM(1) AS quantidade "
            + "FROM j2ee.car AS tabaux "
            + "LEFT JOIN (SELECT nome, cpf FROM j2ee.pessoa) AS tabaux2 ON tabaux.cpf_locador = tabaux2.cpf "
            + "GROUP BY nome; ";
    
    private static final String READ_QUERY_MEDIA_PRECOS_POR_MODELO
            = "SELECT tabaux.modelo AS modelo, ((SUM(preco) * 1.0 )/ tabaux2.quantidade) AS media FROM j2ee.car tabaux "
            + "INNER JOIN (SELECT modelo, SUM(1) AS quantidade FROM j2ee.car GROUP BY modelo) AS tabaux2 ON tabaux2.modelo = tabaux.modelo "
            + "GROUP BY tabaux.modelo, tabaux2.quantidade;";
    
    private static final String READ_QUERY_MONTANTE_RECEBIDO_GASTO
            = "SELECT nome, (CASE WHEN tabaux.valor_ganho IS NULL THEN 0 ELSE tabaux.valor_ganho END "
            + "- CASE WHEN tabaux2.valor_gasto IS NULL THEN 0 ELSE tabaux2.valor_gasto END) AS sobrou "
            + "FROM j2ee.pessoa AS tab "
            + "LEFT JOIN ( SELECT cpf_locador , SUM(valor) AS valor_ganho "
            + "FROM j2ee.pagamento GROUP BY cpf_locador ) AS tabaux ON tabaux.cpf_locador = tab.cpf "
            + "LEFT JOIN (SELECT cpf_locatario, SUM(valor) AS valor_gasto "
            + "FROM j2ee.pagamento GROUP BY cpf_locatario) tabaux2 ON tabaux2.cpf_locatario = tab.cpf;";
    
    private static final String READ_QUERY_CARROS_ADICIONADOS_MENSALMENTE
            = "SELECT to_char(data_pagamento, 'Mon') AS mes, SUM(1) AS carros_adicionados "
            + "FROM j2ee.pagamento "
            + "GROUP BY to_char(data_pagamento, 'Mon');";
    
    private static final String READ_QUERY_QUANTIDADE_CARROS_ANO_MODELO
            = "SELECT modelo, ano, SUM(1) as quantidade "
            + "FROM j2ee.car "
            + "GROUP BY modelo, ano;";
    
    
    public PgEstatisticaDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Estatistica t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estatistica read(Integer id) throws SQLException {
        Estatistica estatistica = new Estatistica();
        
        ArrayList<Integer> valores = new ArrayList<>();
        ArrayList<Double> precos = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        
        String query = null;
        
        switch(id){
            case 1: // Carros por pessoa
                query = READ_QUERY_CARS_PER_PERSON;
            break;
            
            case 2:
                query = READ_QUERY_MEDIA_PRECOS_POR_MODELO;
            break;
            
            case 3:
                query = READ_QUERY_MONTANTE_RECEBIDO_GASTO;
            break;
            
            case 4: 
                query = READ_QUERY_CARROS_ADICIONADOS_MENSALMENTE;
        }
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                        
                    switch(id) {
                        case 1:
                            valores.add(result.getInt("quantidade"));
                            labels.add(result.getString("nome"));
                            break;
                        case 2:
                            precos.add(result.getDouble("media"));
                            labels.add(result.getString("modelo"));
                            break;
                            
                        case 3:
                            precos.add(result.getDouble("sobrou"));
                            labels.add(result.getString("nome"));
                            break;
                        
                        case 4:
                            valores.add(result.getInt("carros_adicionados"));
                            labels.add(result.getString("mes"));

                    }
                    
                }
                
                switch(id) {
                    case 1:
                        estatistica.setValores(valores);
                        estatistica.setLabels(labels);
                        break;
                        
                    case 2:
                        estatistica.setPrecos(precos);
                        estatistica.setLabels(labels);
                        break;
                        
                    case 3:
                        estatistica.setPrecos(precos);
                        estatistica.setLabels(labels);
                        break;
                    
                    case 4:
                        estatistica.setValores(valores);
                        estatistica.setLabels(labels);
                        break;
                       
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgEstatisticaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
            
            if (ex.getMessage().equals("Erro ao visualizar: estatistica não encontrada.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar estatistica.");
            }
        }
        return estatistica;
    }

    @Override
    public void update(Estatistica t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estatistica> all() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
