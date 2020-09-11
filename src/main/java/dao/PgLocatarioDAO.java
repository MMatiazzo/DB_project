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
import model.Locatario;

/**
 *
 * @author mathe
 */
public class PgLocatarioDAO implements LocatarioDAO{
    
    private final Connection connection;
    
    private static final String CREATE_QUERY =
                                "INSERT INTO j2ee.locatario(cpf_pessoa, comp_renda, endereco, habilitacao) " +
                                "VALUES (?, ?, ?, ?);";
    
    private static final String UPDATE_QUERY =
                                "UPDATE j2ee.locatario " +
                                "SET comp_renda = ?, endereco = ?, habilitacao = ? " +
                                "WHERE cpf_pessoa = ?;";
    
     private static final String READ_QUERY =
                                "SELECT cpf_pessoa, comp_renda, endereco, habilitacao " +
                                "FROM j2ee.locatario " +
                                "WHERE cpf_pessoa = ?;";
     
     private static final String DELETE_QUERY =
                                "DELETE FROM j2ee.locatario " +
                                "WHERE cpf_pessoa = ?;";
     
     private static final String ALL_QUERY =
                                "SELECT cpf_pessoa, habilitacao " +
                                "FROM j2ee.locatario;";

    public PgLocatarioDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void create(Locatario locatario) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, locatario.getCpf_pessoa());
            statement.setString(2, locatario.getComp_renda());
            statement.setString(3, locatario.getEndereco());
            statement.setString(4, locatario.getHabilitacao());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgLocatarioDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("pk_cpf_pessoa_locatario")) {
                throw new SQLException("Erro ao inserir locatario: CPF não encontrado.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir locatario: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir locatario.");
            }
        }
    }

    @Override
    public Locatario read(String cpf) throws SQLException {
        Locatario locatario = new Locatario();
        
        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setString(1, cpf);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    locatario.setCpf_pessoa(cpf);
                    locatario.setComp_renda(result.getString("comp_renda"));
                    locatario.setEndereco(result.getString("endereco"));
                    locatario.setHabilitacao(result.getString("habilitacao"));

                } else {
                    throw new SQLException("Erro ao visualizar: locatario não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgLocatarioDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
            
            if (ex.getMessage().equals("Erro ao visualizar: locatario não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar locatario.");
            }
        }

        return locatario;
    }

    @Override
    public void update(Locatario locatario) throws SQLException {
        String query;
        
        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, locatario.getComp_renda());
            statement.setString(2, locatario.getEndereco());
            statement.setString(3, locatario.getHabilitacao());

            statement.setString(4, locatario.getCpf_pessoa());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: locatario não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgLocatarioDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: locatario não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("pk_cpf_pessoa_locatario")) {
                throw new SQLException("Erro ao editar locatario: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar locatario: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar locatario.");
            }
        }
    }

    @Override
    public void delete(String cpf) throws SQLException {
         try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, cpf);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: locatario não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgLocatarioDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: locatario não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir locatario.");
            }
        }
    }

    @Override
    public List<Locatario> all() throws SQLException {
        List<Locatario> locatarioList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Locatario locatario = new Locatario();
                locatario.setCpf_pessoa(result.getString("cpf_pessoa"));
                locatario.setHabilitacao(result.getString("habilitacao"));

                locatarioList.add(locatario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgLocatarioDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar Locatarios.");
        }

        return locatarioList;
    }
    
}
