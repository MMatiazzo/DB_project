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
    
    private static final String READ_QUERY_CARROS_ALUGADOS_MENSALMENTE
            = "SELECT to_char(data_pagamento, 'Mon') AS mes, SUM(1) AS carros_adicionados "
            + "FROM j2ee.pagamento "
            + "GROUP BY to_char(data_pagamento, 'Mon');";
    
    private static final String READ_QUERY_CARROS_MAIS_BEM_AVALIADOS
            = "SELECT tabaux3.nome, tabaux2.modelo, tabaux2.ano, tabaux.num_placa_carro, tabaux.total_notas / tabaux.total_review AS media_review "
            + "FROM ( SELECT num_placa_carro , SUM(nota) AS total_notas , SUM(1) AS total_review FROM j2ee.review GROUP BY num_placa_carro ) AS tabaux "
            + "INNER JOIN (SELECT modelo, ano, cpf_locador, placa FROM j2ee.car)  AS tabaux2 ON tabaux2.placa = tabaux.num_placa_carro "
            + "INNER JOIN (SELECT nome, cpf FROM j2ee.pessoa) AS tabaux3 ON tabaux3.cpf = tabaux2.cpf_locador "
            + "WHERE tabaux.total_review > 0;";
            
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
        ArrayList<ArrayList> colunas = new ArrayList<>();
        
        String query = null;
        
        switch(id){
            case 1: // Carros por pessoa
                query = READ_QUERY_CARS_PER_PERSON;
                colunas.add(new ArrayList<String>());
                colunas.add(new ArrayList<Integer>());
            break;
            
            case 2:
                query = READ_QUERY_MEDIA_PRECOS_POR_MODELO;
                colunas.add(new ArrayList<String>());
                colunas.add(new ArrayList<Double>());
            break;
            
            case 3:
                query = READ_QUERY_MONTANTE_RECEBIDO_GASTO;
                colunas.add(new ArrayList<String>());
                colunas.add(new ArrayList<Double>());
            break;
            
            case 4: 
                query = READ_QUERY_CARROS_ALUGADOS_MENSALMENTE;
                colunas.add(new ArrayList<String>());
                colunas.add(new ArrayList<Integer>());
            break;
                
            case 5:
                query = READ_QUERY_CARROS_MAIS_BEM_AVALIADOS;
                colunas.add(new ArrayList<String>());
                colunas.add(new ArrayList<String>());
                colunas.add(new ArrayList<String>());
                colunas.add(new ArrayList<String>());
                colunas.add(new ArrayList<Integer>());
            break;
        }
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                        
                    switch(id) {
                        case 1:
                            colunas.get(0).add(result.getString("nome"));
                            colunas.get(1).add(result.getInt("quantidade"));                          
                            break;
                        case 2:
                            colunas.get(0).add(result.getString("modelo"));
                            colunas.get(1).add(result.getDouble("media"));
                            break;
                            
                        case 3:
                            colunas.get(0).add(result.getString("nome"));
                            colunas.get(1).add(result.getDouble("sobrou"));
                            break;
                        
                        case 4:
                            colunas.get(0).add(result.getString("mes"));
                            colunas.get(1).add(result.getInt("carros_adicionados"));
                            break;
                            
                        case 5:
                            colunas.get(0).add(result.getString("nome"));
                            colunas.get(1).add(result.getString("modelo"));
                            colunas.get(2).add(result.getString("ano"));
                            colunas.get(3).add(result.getString("num_placa_carro"));
                            colunas.get(4).add(result.getInt("media_review"));
                            break;

                    }
                    
                }
                
                estatistica.setColunas(colunas);
                
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
