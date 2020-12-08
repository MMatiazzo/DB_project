/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Review;

/**
 *
 * @author Guilherme
 */
public interface ReviewDAO extends DAO<Review, ArrayList<String>>{
    public List<Review> all(String placa) throws SQLException;
}
