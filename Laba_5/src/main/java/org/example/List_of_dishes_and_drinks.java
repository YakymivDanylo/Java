package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class List_of_dishes_and_drinks {
    int id_dishes_drinks;
    String type;
    String name;
    String expiration_date;
    public List_of_dishes_and_drinks(int id_dishes_drinks, String type, String name, String expiration_date) {
        this.id_dishes_drinks = id_dishes_drinks;
        this.type = type;
        this.name = name;
        this.expiration_date = expiration_date;
    }

    static public List_of_dishes_and_drinks listFromDB(int id_dishes_drinks) throws Exception{
        String configFile = "D:\\\\java\\\\Laba_5\\\\config.properties";
        try(Connection connection = ConnectionDB.getConnection(configFile);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM 'List_of_dishes_and_drinks'")){
            while(resultSet.next()) {
                if(resultSet.getInt(1)==id_dishes_drinks) {
                    return new List_of_dishes_and_drinks(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception("Problem with connection to DB", e);
        }
        return null;
    }
}
