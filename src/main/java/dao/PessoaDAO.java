/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Pessoa;

/**
 *
 * @author mathe
 */
public interface PessoaDAO extends DAO<Pessoa, String> {
    public void authenticate(Pessoa pessoa) throws SQLException, SecurityException;
    public Pessoa getByLogin(String login) throws SQLException;
}
