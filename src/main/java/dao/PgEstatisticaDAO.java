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
            = "SELECT cpf_locador ,SUM(1) "
            + "FROM j2ee.car "
            + "GROUP BY cpf_locador;";
    
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
        ArrayList<String> labels = new ArrayList<>();
        
        String query = null;
        
        switch(id){
            case 1: // Carros por pessoa
                query = READ_QUERY_CARS_PER_PERSON;
            break;
            
            case 2:
                query = READ_QUERY_QUANTIDADE_CARROS_ANO_MODELO;
            break;
        }
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    valores.add(result.getInt("quantidade"));
                    labels.add(result.getString("ano"));
                    
                }
                estatistica.setValores(valores);
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
