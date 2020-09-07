/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.User;

/**
 *
 * @author dskaster
 */
public interface UserDAO extends DAO<User> {

    public void authenticate(User usuario) throws SQLException, SecurityException;
}