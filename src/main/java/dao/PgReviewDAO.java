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
import model.Review;

/**
 *
 * @author Guilherme
 */
public class PgReviewDAO implements ReviewDAO{
    
    private final Connection connection;

    private static final String CREATE_QUERY
            = "INSERT INTO j2ee.review(num_placa_carro, cpf_locador, cpf_locatario, descricao, nota, data_review) "
            + "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_QUERY
            = "UPDATE j2ee.review "
            + "SET descricao = ?, nota = ?, data_review = ? "
            + "WHERE num_placa_carro = ? AND cpf_locador = ? AND cpf_locatario = ?;";

    private static final String READ_QUERY
            = "SELECT num_placa_carro, cpf_locador, cpf_locatario, descricao, nota, data_review "
            + "FROM j2ee.review "
            + "WHERE num_placa_carro = ? AND cpf_locador = ? AND cpf_locatario = ?;";

    private static final String DELETE_QUERY
            = "DELETE FROM j2ee.review "
            + "WHERE num_placa_carro = ? AND cpf_locador = ? AND cpf_locatario = ?;";

    private static final String ALL_QUERY
            = "SELECT num_placa_carro, cpf_locador, cpf_locatario, data_review, descricao, nota "
            + "FROM j2ee.review;";
    
    private static final String SEARCH_BY_PLACA
            = "SELECT num_placa_carro, cpf_locador, cpf_locatario, data_review, descricao, nota, nome, avatar "
            + "FROM j2ee.review, j2ee.pessoa "
            + "WHERE num_placa_carro = ? AND cpf = cpf_locatario;";

    public PgReviewDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Review review) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, review.getNum_placa_carro());
            statement.setString(2, review.getCpf_locador());
            statement.setString(3, review.getCpf_locatario());
            statement.setString(4, review.getDescricao());
            statement.setInt(5, review.getNota());
            statement.setDate(6, review.getData_review());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgReviewDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("pk_review")) {
                throw new SQLException("Erro ao inserir review: review já existente.");
            } else if (ex.getMessage().contains("fk_review_num_placa_carro")) {
                throw new SQLException("Erro ao inserir review: carro não cadastrado.");
            } else if (ex.getMessage().contains("fk_review_cpf_locador")) {
                throw new SQLException("Erro ao inserir review: locador não cadastrado.");
            }else if (ex.getMessage().contains("fk_review_cpf_locatario")) {
                throw new SQLException("Erro ao inserir review: locatario não cadastrado.");
            }else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir review: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir review.");
            }
        }
    }

    @Override
    public Review read(ArrayList<String> keys) throws SQLException {
        Review review = new Review();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setString(1, keys.get(0));
            statement.setString(2, keys.get(1));
            statement.setString(3, keys.get(2));
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    review.setNum_placa_carro(keys.get(0));
                    review.setCpf_locador(result.getString("cpf_locador"));
                    review.setCpf_locatario(result.getString("cpf_locatario"));
                    review.setDescricao(result.getString("descricao"));
                    review.setNota(result.getInt("nota"));
                    review.setData_review(result.getDate("data_review"));
                } else {
                    throw new SQLException("Erro ao visualizar: review não encontrada.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgReviewDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
            
            if (ex.getMessage().equals("Erro ao visualizar: review não encontrada.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar review.");
            }
        }

        return review;
    }

    @Override
    public void update(Review review) throws SQLException {
        String query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, review.getDescricao());
            statement.setInt(2, review.getNota());
            statement.setDate(3, review.getData_review());
            statement.setString(4, review.getNum_placa_carro());
            statement.setString(5, review.getCpf_locador());
            statement.setString(6, review.getCpf_locatario());

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: review não encontrada.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgReviewDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: review não encontrada.")) {
                throw ex;
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar review: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar review.");
            }
        }
    }

    @Override
    public void delete(ArrayList<String> keys) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, keys.get(0));
            statement.setString(2, keys.get(1));
            statement.setString(3, keys.get(2));

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: review não encontrada.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgReviewDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: review não encontrada.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir review.");
            }
        }
    }

    @Override
    public List<Review> all() throws SQLException {
        List<Review> reviewList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Review review = new Review();
                review.setNum_placa_carro(result.getString("num_placa_carro"));
                review.setCpf_locador(result.getString("cpf_locador"));
                review.setCpf_locatario(result.getString("cpf_locatario"));
                review.setData_review(result.getDate("data_review"));
                review.setDescricao(result.getString("descricao"));
                review.setNota(result.getInt("nota"));

                reviewList.add(review);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgReviewDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar Reviews.");
        }

        return reviewList;
    }
    
    
    @Override
    public List<Review> all(String placa) throws SQLException {
        List<Review> reviewList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SEARCH_BY_PLACA)){
                statement.setString(1, placa);
                ResultSet result = statement.executeQuery();
            while (result.next()) {
                Review review = new Review();
                review.setNum_placa_carro(result.getString("num_placa_carro"));
                review.setCpf_locador(result.getString("avatar"));
                review.setCpf_locatario(result.getString("nome"));
                review.setData_review(result.getDate("data_review"));
                review.setDescricao(result.getString("descricao"));
                review.setNota(result.getInt("nota"));

                reviewList.add(review);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgReviewDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar Reviews.");
        }

        return reviewList;
    }




}
