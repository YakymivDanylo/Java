package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ConnectionDB {
    static Properties readeConfiguaration (String filename){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(filename));
        } catch(IOException e){
            System.out.println("Error! "+e.getMessage());
        }
        return properties;
    }

    public static Connection getConnection(String filename) throws SQLException{
        Properties properties = readeConfiguaration(filename);
        return DriverManager.getConnection(properties.getProperty("jdbc:postgresql://localhost:5432/Cafe?currentSchema=\"Cafe\""),properties.getProperty("username"),properties.getProperty("password"));
    }
}
