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
import model.Car;

/**
 *
 * @author Guilherme
 */
public class PgCarDAO implements CarDAO {

    private final Connection connection;

    private static final String CREATE_QUERY
            = "INSERT INTO j2ee.car(placa, abss, modelo, tipo, ar_condicionado, airbags, num_lugares, descricao, disponibilidade, cpf_locador, avatar) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String READ_QUERY
            = "SELECT placa, abss, modelo, tipo, ar_condicionado, airbags, num_lugares, descricao, disponibilidade, cpf_locador, avatar "
            + "FROM j2ee.car "
            + "WHERE placa = ?;";

    private static final String UPDATE_QUERY
            = "UPDATE j2ee.car "
            + "SET abss = ?, modelo = ?,  tipo = ?, ar_condicionado = ?, airbags = ?, num_lugares = ?, descricao = ?, disponibilidade = ?, cpf_locador = ? "
            + "WHERE placa = ?;";

    private static final String UPDATE_WITH_AVATAR_QUERY
            = "UPDATE j2ee.car "
            + "SET abss = ?, modelo = ?,  tipo = ?, ar_condicionado = ?, airbags = ?, num_lugares = ?, descricao = ?, disponibilidade = ?, cpf_locador = ?, avatar = ? "
            + "WHERE placa = ?;";

    private static final String DELETE_QUERY
            = "DELETE FROM j2ee.car "
            + "WHERE placa = ?;";

    private static final String ALL_QUERY
            = "SELECT placa, modelo, avatar, disponibilidade, preco, descricao, num_lugares, airbags, abss, ar_condicionado "
            + "FROM j2ee.car;";
    
    private static final String[] ALL_QUERY_ORDER_BY
            = {"SELECT placa, modelo, avatar, disponibilidade, preco, descricao "
            + "FROM j2ee.car " 
            + "ORDER BY modelo ASC;",
            "SELECT placa, modelo, avatar, disponibilidade, preco, descricao "
            + "FROM j2ee.car " 
            + "ORDER BY preco ASC;",
            "SELECT placa, modelo, avatar, disponibilidade, preco, descricao "
            + "FROM j2ee.car " 
            + "ORDER BY ano ASC;"};
    
     private static final String ALL_QUERY_FILTERED
            = "SELECT placa, modelo, avatar, disponibilidade, preco, descricao, ano "
            + "FROM j2ee.car " 
            + "WHERE modelo = ? AND ano = ? AND preco <= ?;";
     
     private static final String SEARCH_QUERY
            = "SELECT * FROM j2ee.car " +
              "WHERE modelo LIKE ? " +
              "OR descricao LIKE ? ";

    public PgCarDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Car car) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY)) {
            
            statement.setString(1, car.getPlaca());
            statement.setBoolean(2, car.isAbss());
            statement.setString(3, car.getModelo());
            statement.setString(4, car.getTipo());
            statement.setBoolean(5, car.isAr_condicionado());
            statement.setBoolean(6, car.isAirbags());
            statement.setInt(7, car.getNum_lugares());
            statement.setString(8, car.getDescricao());
            statement.setBoolean(9, car.isDisponibilidade());
            statement.setString(10, car.getCpf_locador());
            statement.setString(11, car.getAvatar());

            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PgCarDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().contains("pk_placa")) {
                throw new SQLException("Erro ao inserir carro: placa ja existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir carro: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao inserir carro.");
            }
        }
    }

    @Override
    public Car read(String id) throws SQLException {
        Car car = new Car();
        try (PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setString(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    car.setPlaca(id);
                    car.setAbss(result.getBoolean("abss"));
                    car.setModelo(result.getString("modelo"));
                    car.setTipo(result.getString("tipo"));
                    car.setAr_condicionado(result.getBoolean("ar_condicionado"));
                    car.setAirbags(result.getBoolean("airbags"));
                    car.setNum_lugares(result.getInt("num_lugares"));
                    car.setDescricao(result.getString("descricao"));
                    car.setDisponibilidade(result.getBoolean("disponibilidade"));
                    car.setCpf_locador(result.getString("cpf_locador"));
                    car.setAvatar(result.getString("avatar"));
                } else {
                    throw new SQLException("Erro ao visualizar: Carro não encontrado.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgCarDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao visualizar: Carro não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao visualizar usuário.");
            }
        }
        return car;
    }

    @Override
    public void update(Car car) throws SQLException {
        String query;

        if ((car.getAvatar() == null) || (car.getAvatar().isBlank())) {
            query = UPDATE_QUERY;
        } else {
            query = UPDATE_WITH_AVATAR_QUERY;
        }
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, car.isAbss());
            statement.setString(2, car.getModelo());
            statement.setString(3, car.getTipo());
            statement.setBoolean(4, car.isAr_condicionado());
            statement.setBoolean(5, car.isAirbags());
            statement.setInt(6, car.getNum_lugares());
            statement.setString(7, car.getDescricao());
            statement.setBoolean(8, car.isDisponibilidade());
            statement.setString(9, car.getCpf_locador());
            
            if (car.getAvatar() == null || car.getAvatar().isBlank()){
                statement.setString(10, car.getPlaca());
            } else {
                statement.setString(10, car.getAvatar());
                statement.setString(11, car.getPlaca());
            }

            

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: carro não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgCarDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao editar: carro não encontrado.")) {
                throw ex;
            } else if (ex.getMessage().contains("pk_placa")) {
                throw new SQLException("Erro ao editar carro: placa já existente.");
            } else if (ex.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar carro: pelo menos um campo está em branco.");
            } else {
                throw new SQLException("Erro ao editar carro.");
            }
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao excluir: placa não encontrado.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgCarDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            if (ex.getMessage().equals("Erro ao excluir: carro não encontrado.")) {
                throw ex;
            } else {
                throw new SQLException("Erro ao excluir carro.");
            }
        }
    }

    @Override
    public List<Car> all() throws SQLException {
        List<Car> carList = new ArrayList<>();


        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Car car = new Car();
                car.setPlaca(result.getString("placa"));
                car.setModelo(result.getString("modelo"));
                car.setAvatar(result.getString("avatar"));
                car.setPreco(result.getDouble("preco"));
                car.setDisponibilidade(result.getBoolean("disponibilidade"));
                car.setDescricao(result.getString("descricao"));
                car.setNum_lugares(result.getInt("num_lugares"));
                car.setAirbags(result.getBoolean("airbags"));
                car.setAbss(result.getBoolean("abss"));
                car.setAr_condicionado(result.getBoolean("ar_condicionado"));
                
                
                
                carList.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgCarDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar carros.");
        }

        return carList;
    }

   

    @Override
    public List<Car> all(String order_by) throws SQLException {
        List<Car> carList = new ArrayList<>();
        int i = 0;
        switch(order_by) {
            case "modelo": {
                i = 0;
                break;
            }
            
            case "preco": {
                i = 1;
                break;
            }
            
            case "ano": {
                i = 2;
                break;
            }
        }
        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY_ORDER_BY[i])) {
            System.out.println(order_by);
            
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Car car = new Car();
                car.setPlaca(result.getString("placa"));
                car.setModelo(result.getString("modelo"));
                car.setAvatar(result.getString("avatar"));
                car.setPreco(result.getDouble("preco"));
                car.setDisponibilidade(result.getBoolean("disponibilidade"));
                car.setDescricao(result.getString("descricao"));
                
                carList.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgCarDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar carros.");
        }

        return carList;
    }
    
    public List<Car> all(String modelo, int ano, double preco) throws SQLException {
        List<Car> carList = new ArrayList<>();
        
        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY_FILTERED)) {
            System.out.println(modelo);
            System.out.println(ano);
            System.out.println(preco);
            
            statement.setString(1, modelo);
            statement.setInt(2, ano);
            statement.setDouble(3, preco);
            
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Car car = new Car();
                car.setPlaca(result.getString("placa"));
                car.setModelo(result.getString("modelo"));
                car.setAvatar(result.getString("avatar"));
                car.setPreco(result.getDouble("preco"));
                car.setDisponibilidade(result.getBoolean("disponibilidade"));
                car.setDescricao(result.getString("descricao"));
                car.setAno(result.getInt("ano"));
                
                carList.add(car); 
            }
        }catch (SQLException ex) {
            Logger.getLogger(PgCarDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar carros.");
        }

        return carList;
    }
    
    public List<Car> search(String search_by) throws SQLException {
        List<Car> carList = new ArrayList<>();
        String[] splited = search_by.split("\\s+");
        String query = SEARCH_QUERY;
        int words = splited.length;
        
        for(int i = 0; i < words; i++){
            splited[i] = "%" + splited[i] + "%";
            
            if(i > 0)
                query += "AND (modelo LIKE ? " +
                         "OR descricao LIKE ?) "; 
        }
        
        
        
  
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for(int i = 0; i <= words; i+=2){
                statement.setString(i+1, splited[i]);
                statement.setString(i+2, splited[i]);
            }
                
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Car car = new Car();
                car.setPlaca(result.getString("placa"));
                car.setModelo(result.getString("modelo"));
                car.setAvatar(result.getString("avatar"));
                car.setPreco(result.getDouble("preco"));
                car.setDisponibilidade(result.getBoolean("disponibilidade"));
                car.setDescricao(result.getString("descricao"));
                
                carList.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PgCarDAO.class.getName()).log(Level.SEVERE, "DAO", ex);

            throw new SQLException("Erro ao listar carros.");
        }

        return carList;
    }
}

