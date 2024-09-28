package org.example;

import org.example.admin.*;
import org.example.client.*;
import java.sql.*;

public class Authorizer {
    public AdminDAO adminAuthorization(String name,String password) throws Exception{
        String configFilename = "D:\\java\\Laba_5\\config.properties";

        try(Connection connection = ConnectionDB.getConnection(configFilename);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM 'admin'")) {
            while (resultSet.next()) {
                if (resultSet.getString(2).equals(name) && resultSet.getString(6).equals(password)) {
                    AdminDTO admin = new AdminDTO(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    );
                    return new AdminDAO(admin);
                }
            }
        }catch (SQLException e) {
            System.out.println("Error! "+e.getMessage());
            throw new Exception("Problem with connection to DB", e);
        }
        return null;
    }

    public ClientDAO clientAuthorization(String name,String password) throws Exception{
        String configFilename = "D:\\java\\Laba_5\\client.properties";
        try(Connection connection = ConnectionDB.getConnection(configFilename);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM 'client'")) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(name) && resultSet.getString(6).equals(password)) {
                    ClientDTO client = new ClientDTO(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    );
                    return new ClientDAO(client);
                }
            }
        }
        catch (SQLException e){
            System.out.println("Error! "+e.getMessage());
            throw new Exception("Problem with connection to DB", e);
        }
        return null;
    }
}
