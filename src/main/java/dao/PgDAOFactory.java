/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;

/**
 *
 * @author dskaster
 */
public class PgDAOFactory extends DAOFactory {

    public PgDAOFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserDAO getUserDAO() {
        return new PgUserDAO(this.connection);
    }

    @Override
    public CarDAO getCarDAO() {
        return new PgCarDAO(this.connection);
    }

    @Override
    public PessoaDAO getPessoaDAO() {
        return new PgPessoaDAO(this.connection);
    }
    
    @Override
    public LocadorDAO getLocadorDAO(){
        return new PgLocadorDAO(this.connection);
    }
    
    @Override
    public LocatarioDAO getLocatarioDAO() {
       return new PgLocatarioDAO(this.connection);
    }
    
    @Override
    public ReviewDAO getReviewDAO() {
        return new PgReviewDAO(this.connection);
    }
   

}
