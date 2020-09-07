/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author dskaster
 */
public abstract class ConnectionFactory {
    //Aqui temos a instancia que eu quero trabalhar 
    private static ConnectionFactory instance = null;
    //Arquivo de propriedades onde vão estar armazenadas as informações de conexão
    protected static String propertiesPath = "../../conf/datasource.properties";
    //Aqui é pra saber qual servidor foi instanciado, pra poder instanciar a fabrica correspondente aquela conexão
    private static String dbServer;
    
    protected ConnectionFactory() {
    }

    public static ConnectionFactory getInstance() throws IOException {
        if (instance == null) {
            Properties properties = new Properties();

            try {
                InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream(propertiesPath);
                properties.load(input);

                dbServer = properties.getProperty("server");
            } catch (IOException ex) {
                System.err.println(ex.getMessage());

                throw new IOException("Erro ao obter informações do banco de dados.");
            }

            if (getDbServer().equals("postgresql")) {
                instance = new PgConnectionFactory();
            }
            else {
                throw new RuntimeException("Servidor de banco de dados não suportado.");
            }
        }

        return instance;
    }
    
    /**
     * @return the dbServer
     */
    public static String getDbServer() {
        return dbServer;
    }

    public abstract Connection getConnection() throws IOException, SQLException, ClassNotFoundException;
    
}
