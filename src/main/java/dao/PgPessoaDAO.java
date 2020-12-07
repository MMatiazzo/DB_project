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
import model.Pessoa;
/**
 *
 * @author mathe
 */
public class PgPessoaDAO implements PessoaDAO {
    
    private final Connection connection;
    
    private static final String CREATE_QUERY =
                                "INSERT INTO j2ee.pessoa(cpf, login, senha, nome, nascimento, avatar) " +
                                "VALUES (?, ?, md5(?), ?, ?, ?);";
    
    private static final String UPDATE_QUERY =
                                "UPDATE j2ee.pessoa " +
                                "SET login = ?, nome = ?, nascimento = ? " +
                                "WHERE cpf = ?;";
    
    private static final String UPDATE_WITH_PASSWORD_QUERY =
                                "UPDATE j2ee.pessoa " +
                                "SET login = ?, nome = ?, nascimento = ?, senha = md5(?) " +
                                "WHERE cpf = ?;";
    
    private static final String UPDATE_WITH_AVATAR_QUERY =
                                "UPDATE j2ee.pessoa " +
                                "SET login = ?, nome = ?, nascimento = ?, avatar = ? " +
                                "WHERE cpf = ?;";

    private static final String UPDATE_WITH_AVATAR_AND_PASSWORD_QUERY =
                                "UPDATE j2ee.pessoa " +
                                "SET login = ?, nome = ?, nascimento = ?, avatar = ?, senha = md5(?) " +
                                "WHERE cpf = ?;";
    
     private static final String READ_QUERY =
                                "SELECT cpf, login, nome, nascimento, avatar " +
                                "FROM j2ee.pessoa " +
                                "WHERE cpf = ?;";
     
     private static final String DELETE_QUERY =
                                "DELETE FROM j2ee.pessoa " +
                                "WHERE cpf = ?;";
     
     private static final String ALL_QUERY =
                                "SELECT cpf, login " +
                                "FROM j2ee.pessoa ";
     
     private static final String AUTHENTICATE_QUERY =
                                "SELECT cpf, nome, nascimento, avatar " +
                                "FROM j2ee.pessoa " +
                                "WHERE login = ? AND senha = md5(?);";
     
     private static final String GET_BY_LOGIN_QUERY =
                                "SELECT cpf, login, nome, nascimento, avatar " +
                                "FROM j2ee.pessoa " +
                                "WHERE login = ?;";
    
    public PgPessoaDAO(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void create(Pessoa pessoa) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, pessoa.getCpf());
            statement.setString(2, pessoa.getLogin());
            statement.setString(3, pessoa.getSenha());
            statement.setString(4, pessoa.getNome());
            statement.setDate(5, pessoa.getNascimento());
            statement.setString(6, pessoa.getAvatar());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgPessoaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("uq_pessoa_login")) {
                throw new SQLException("Erro ao inserir Pessoa: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir pessoa: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir pessoa.");
            }
        }
    }

     @Override
    public Pessoa read(String cpf) throws SQLException {
        Pessoa pessoa = new Pessoa();
        
        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setString(1, cpf);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    pessoa.setCpf(cpf);
                    pessoa.setLogin(result.getString("login"));
                    pessoa.setNome(result.getString("nome"));
                    pessoa.setNascimento(result.getDate("nascimento"));
                    pessoa.setAvatar(result.getString("avatar"));
                } else {
                    throw new SQLException("Erro ao visualizar: pessoa não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPessoaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
            
            if (ex.getMessage().equals("Erro ao visualizar: pessoa não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar pessoa.");
            }
        }

        return pessoa;
    }
    
     @Override
    public void delete(String cpf) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, cpf);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: pessoa não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPessoaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: pessoa não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir pessoa.");
            }
        }
    }

    @Override
    public List<Pessoa> all() throws SQLException {
        List<Pessoa> pessoaList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setCpf(result.getString("cpf"));
                pessoa.setLogin(result.getString("login"));

                pessoaList.add(pessoa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPessoaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar Pessoas.");
        }

        return pessoaList;
    }

    @Override
    public void update(Pessoa pessoa) throws SQLException {
        String query;

        if ((pessoa.getSenha() == null) || (pessoa.getSenha().isBlank())) {
            if ((pessoa.getAvatar() == null) || (pessoa.getAvatar().isBlank()))
                query = UPDATE_QUERY;
            else
                query = UPDATE_WITH_AVATAR_QUERY;
        } else {
            if ((pessoa.getAvatar() == null) || (pessoa.getAvatar().isBlank()))
                query = UPDATE_WITH_PASSWORD_QUERY;
            else
                query = UPDATE_WITH_AVATAR_AND_PASSWORD_QUERY;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pessoa.getLogin());
            statement.setString(2, pessoa.getNome());
            statement.setDate(3, pessoa.getNascimento());

            if ((pessoa.getSenha() == null) || (pessoa.getSenha().isBlank())) {
                if ((pessoa.getAvatar() == null) || (pessoa.getAvatar().isBlank())) {
                    statement.setString(4, pessoa.getCpf());
                    
                } else {
                    statement.setString(4, pessoa.getAvatar());
                    statement.setString(5, pessoa.getCpf());
                }
            } else {
                if ((pessoa.getAvatar() == null) || (pessoa.getAvatar().isBlank())) {
                    statement.setString(4, pessoa.getSenha());
                    statement.setString(5, pessoa.getCpf());
                } else {
                    statement.setString(4, pessoa.getAvatar());
                    statement.setString(5, pessoa.getSenha());
                    statement.setString(6, pessoa.getCpf());
                }
            }

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: pessoa não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPessoaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: pessoa não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("uq_pessoa_login")) {
                throw new SQLException("Erro ao editar pessoa: login já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar pessoa: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar pessoa.");
            }
        }
    }

    @Override
    public void authenticate(Pessoa pessoa) throws SQLException, SecurityException {
        try (PreparedStatement statement = connection.prepareStatement(AUTHENTICATE_QUERY)) {
            statement.setString(1, pessoa.getLogin());
            statement.setString(2, pessoa.getSenha());

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    pessoa.setCpf(result.getString("cpf"));
                    pessoa.setNome(result.getString("nome"));
                    pessoa.setNascimento(result.getDate("nascimento"));
                    pessoa.setAvatar(result.getString("avatar"));
                } else {
                    throw new SecurityException("Login ou senha incorretos.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPessoaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao autenticar usuário.");
        }
    }

    @Override
    public Pessoa getByLogin(String login) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN_QUERY)) {
            statement.setString(1, login);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setCpf(result.getString("id"));
                    pessoa.setNome(result.getString("nome"));
                    pessoa.setNascimento(result.getDate("nascimento"));
                    pessoa.setAvatar(result.getString("avatar"));
                    pessoa.setLogin(login);
                    return pessoa;

                } else {

                    return null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPessoaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
            
            throw new SQLException("Erro ao obter usuário.");
        }
    }
}
