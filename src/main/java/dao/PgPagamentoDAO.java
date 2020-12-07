/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pagamento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathe
 */
public class PgPagamentoDAO implements PagamentoDAO{
    
    private final Connection connection;
    
    private static final String CREATE_QUERY =
                                "INSERT INTO j2ee.pagamento(data_pagamento, num_placa_carro, cpf_locador, cpf_locatario, valor, data_entrega, data_devolucao) " +
                                "VALUES(?, ?, ?, ?, ?, ?, ?);";

    private static final String READ_QUERY =
                                "SELECT data_pagamento, num_placa_carro, cpf_locador, cpf_locatario, valor, data_entrega, data_devolucao " +
                                "FROM j2ee.pagamento " +
                                "WHERE data_pagamento = ? AND num_placa_carro = ? AND cpf_locador = ? AND cpf_locatario = ?;";

    private static final String UPDATE_QUERY =
                                "UPDATE j2ee.pagamento " +
                                "SET valor = ?, data_entrega = ? , data_devolucao = ? " +
                                "WHERE data_pagamento = ? AND num_placa_carro = ? AND cpf_locador = ? AND cpf_locatario = ?;";
    
    private static final String DELETE_QUERY =
                                "DELETE FROM j2ee.pagamento " +
                                "WHERE data_pagamento = ? AND num_placa_carro = ? AND cpf_locador = ? AND cpf_locatario = ?;";

    private static final String ALL_QUERY =
                                "SELECT data_pagamento, num_placa_carro, cpf_locador, cpf_locatario, valor, data_entrega, data_devolucao " +
                                "FROM j2ee.pagamento;";
    
    public PgPagamentoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Pagamento pagamento) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            statement.setDate(1, pagamento.getData_pagamento());
            statement.setString(2, pagamento.getNum_placa_carro());
            statement.setString(3, pagamento.getCpf_locador());
            statement.setString(4, pagamento.getCpf_locatario());
            statement.setDouble(5, pagamento.getValor());
            statement.setDate(6, pagamento.getData_entrega());
            statement.setDate(7, pagamento.getData_devolucao());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgPagamentoDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir pagamento: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir usuário.");
            }
        }
    }

    @Override
    public Pagamento read(ArrayList<String> pagamentos) throws SQLException {

       java.util.Date dataPagamento;

       Pagamento pag = new Pagamento();

        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            dataPagamento = new SimpleDateFormat("yyyy-mm-dd").parse(pagamentos.get(0));
            statement.setDate(1, new Date(dataPagamento.getTime()));
            statement.setString(2, pagamentos.get(1));
            statement.setString(3, pagamentos.get(2));
            statement.setString(4, pagamentos.get(3));
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    pag.setData_pagamento(new Date(dataPagamento.getTime()));
                    pag.setNum_placa_carro(result.getString("num_placa_carro"));
                    pag.setCpf_locador(result.getString("cpf_locador"));
                    pag.setCpf_locatario(result.getString("cpf_locatario"));
                    pag.setValor(result.getDouble("valor"));
                    pag.setData_entrega(result.getDate("data_entrega"));
                    pag.setData_devolucao(result.getDate("data_devolucao"));
                } else {
                    throw new SQLException("Erro ao visualizar: pagamento não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPagamentoDAO.class.getName()).log(Level.SEVERE, "DAO", ex);
            
            if (ex.getMessage().equals("Erro ao visualizar: pagamento não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar pagamento.");
            }
        } catch (ParseException ex) {
            Logger.getLogger(PgPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pag;
    }

    @Override
    public void update(Pagamento pagamento) throws SQLException {
        String query;
        
        query = UPDATE_QUERY;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, pagamento.getValor());
            statement.setDate(2, pagamento.getData_entrega());
            statement.setDate(3, pagamento.getData_devolucao());
            statement.setDate(4, pagamento.getData_pagamento());
            statement.setString(5, pagamento.getNum_placa_carro());
            statement.setString(6, pagamento.getCpf_locador());
            statement.setString(7, pagamento.getCpf_locatario());
            

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: pagamento não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPagamentoDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: pagamento não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar pagamento: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar pagamento.");
            }
        }
    }

    @Override
    public void delete(ArrayList<String> pagamentos) throws SQLException {

        java.util.Date dataPagamento;

        
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            dataPagamento = new SimpleDateFormat("yyyy-mm-dd").parse(pagamentos.get(0));
            statement.setDate(1, new Date(dataPagamento.getTime()));
            statement.setString(2, pagamentos.get(1));
            statement.setString(3, pagamentos.get(2));
            statement.setString(4, pagamentos.get(3));

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: pagamento não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPessoaDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: pagamento não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir pagamento.");
            }
        } catch (ParseException ex) {
            Logger.getLogger(PgPagamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Pagamento> all() throws SQLException {
        List<Pagamento> pagamentoList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setNum_placa_carro(result.getString("num_placa_carro"));
                pagamento.setData_pagamento(result.getDate("data_pagamento"));
                pagamento.setCpf_locador(result.getString("cpf_locador"));
                pagamento.setCpf_locatario(result.getString("cpf_locatario"));
                pagamento.setData_entrega(result.getDate("data_entrega"));
                pagamento.setData_devolucao(result.getDate("data_devolucao"));
                pagamento.setValor(result.getDouble("valor"));

                pagamentoList.add(pagamento);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgPagamentoDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar Pagamentos.");
        }

        return pagamentoList;
    }
    
}
