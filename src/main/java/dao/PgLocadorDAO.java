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
import model.Locador;

/**
 *
 * @author Guilherme
 */
public class PgLocadorDAO implements LocadorDAO {

    private final Connection connection;

    private static final String CREATE_QUERY
            = "INSERT INTO j2ee.locador(cpf_pessoa, doc_carro) "
            + "VALUES (?, ?);";

    private static final String UPDATE_QUERY
            = "UPDATE j2ee.locador "
            + "SET doc_carro = ? "
            + "WHERE cpf_pessoa = ?;";

    private static final String READ_QUERY
            = "SELECT cpf_pessoa, doc_carro "
            + "FROM j2ee.locador "
            + "WHERE cpf_pessoa = ?;";

    private static final String DELETE_QUERY
            = "DELETE FROM j2ee.locador "
            + "WHERE cpf_pessoa = ?;";

    private static final String ALL_QUERY
            = "SELECT cpf_pessoa, doc_carro "
            + "FROM j2ee.locador ";

    public PgLocadorDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Locador locador) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, locador.getCpf_pessoa());
            statement.setString(2, locador.getDoc_carro());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgLocadorDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("pk_cpf_pessoa")) {
                throw new SQLException("Erro ao inserir locador: cpf não encontrado");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir locador: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir locador.");
            }
        }
    }

    @Override
    public Locador read(String cpf) throws SQLException {
        Locador locador = new Locador();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setString(1, cpf);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    locador.setCpf_pessoa(cpf);
                    locador.setDoc_carro(result.getString("doc_carro"));
                } else {
                    throw new SQLException("Erro ao visualizar: pessoa não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgLocadorDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: locador não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar locador.");
            }
        }

        return locador;
    }

    @Override
    public void update(Locador locador) throws SQLException {
        String query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, locador.getDoc_carro());
            statement.setString(2, locador.getCpf_pessoa());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: locador não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgLocadorDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: locador não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar locador: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar locador.");
            }
        }
    }

    @Override
    public void delete(String cpf) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, cpf);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: locador não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgLocadorDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: locador não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir locador.");
            }
        }
    }

    @Override
    public List<Locador> all() throws SQLException {
        List<Locador> locadorList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Locador locador = new Locador();
                locador.setCpf_pessoa(result.getString("cpf_pessoa"));
                locador.setDoc_carro(result.getString("doc_carro"));

                locadorList.add(locador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgLocadorDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar Locadores.");
        }

        return locadorList;
    }
}