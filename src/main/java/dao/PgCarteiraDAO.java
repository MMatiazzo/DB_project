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
import model.Carteira;

/**
 *
 * @author Guilherme
 */
public class PgCarteiraDAO implements CarteiraDAO{
    
    private final Connection connection;

    private static final String CREATE_QUERY
            = "INSERT INTO j2ee.carteira(cpf, saldo) "
            + "VALUES(?, ?);";

    private static final String READ_QUERY
            = "SELECT cpf, saldo "
            + "FROM j2ee.carteira "
            + "WHERE cpf = ?;";

    private static final String UPDATE_QUERY
            = "UPDATE j2ee.carteira "
            + "SET saldo = ? "
            + "WHERE cpf = ?;";
    
    private static final String DELETE_QUERY
            = "DELETE FROM j2ee.carteira "
            + "WHERE cpf = ?;";

    private static final String ALL_QUERY
            = "SELECT cpf, saldo "
            + "FROM j2ee.carteira;";
    
    private static final String ALL_QUERY_ORDER_BY_SALDO
            = "SELECT cpf, saldo "
            + "FROM j2ee.carteira " 
            + "ORDER BY saldo DESC;";


    public PgCarteiraDAO(Connection connection) {
        this.connection = connection;
    }
    

    @Override
    public void create(Carteira cart) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            
            statement.setString(1, cart.getCpf());
            statement.setDouble(2, cart.getSaldo());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgCarteiraDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("pk_placa")) {
                throw new SQLException("Erro ao inserir carteira: cpf ja existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir carteira: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir carteira.");
            }
        }
    }

    @Override
    public Carteira read(String cpf) throws SQLException {
        Carteira carteira = new Carteira();
        
        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setString(1, cpf);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    carteira.setCpf(cpf);
                    carteira.setSaldo(result.getDouble("saldo"));
                } else {
                    throw new SQLException("Erro ao visualizar: carteira não encontrada.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgCarteiraDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
            
            if (ex.getMessage().equals("Erro ao visualizar: carteira não encontrada.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar carteira.");
            }
        }
        return carteira;
    }

    @Override
    public void update(Carteira carteira) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setDouble(1, carteira.getSaldo());
            statement.setString(2, carteira.getCpf());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: carteira não encontrada.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgCarteiraDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: carteira não encontrada.")) {
                throw ex;
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar carteira: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar carteira.");
            }
        }
    }

    @Override
    public void delete(String cpf) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, cpf);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: carteira não encontrada.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgCarteiraDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: carteira não encontrada.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir carteira.");
            }
        }
    }

    @Override
    public List<Carteira> all() throws SQLException {
        List<Carteira> carteiraList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Carteira carteira = new Carteira();
                carteira.setCpf(result.getString("cpf"));
                carteira.setSaldo(result.getDouble("saldo"));

                carteiraList.add(carteira);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgCarteiraDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar carteiras.");
        }

        return carteiraList;
    }
    
}
