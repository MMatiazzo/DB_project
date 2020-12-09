/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.SQLException;
import java.util.List;
import model.Car;

/**
 *
 * @author Guilherme
 */
public interface CarDAO extends DAO<Car, String>{
    public List<Car> all(String order_by) throws SQLException;
    public List<Car> all(String modelo, int ano, double preco) throws SQLException;
}
