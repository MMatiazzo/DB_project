/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Carteira;

/**
 *
 * @author Guilherme
 */
public interface CarteiraDAO extends DAO<Carteira, String>{
    public void update(Carteira carteira, Carteira carteira2) throws SQLException;
}
