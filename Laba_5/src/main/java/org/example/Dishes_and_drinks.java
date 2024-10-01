package org.example;

import lombok.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Getter
public class Dishes_and_drinks {
    int id_of_dishes_and_drinks;
    String type;
    String name;
    String expiration_date;
    double price;
    public Dishes_and_drinks(int id_of_dishes_and_drinks, String type, String name, String expiration_date, double price) {
        this.id_of_dishes_and_drinks = id_of_dishes_and_drinks;
        this.type = type;
        this.name = name;
        this.expiration_date = expiration_date;
        this.price = price;
    }

    static public Dishes_and_drinks listFromDB(int id_dishes_drinks) throws Exception{
        String configFile = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";
        try(Connection connection = ConnectionDB.getConnection(configFile);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM List_of_dishes_and_drinks")){
            while(resultSet.next()) {
                if(resultSet.getInt(1)==id_dishes_drinks) {
                    return new Dishes_and_drinks(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDouble(5)
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception("Problem with connection to DB", e);
        }
        return null;
    }
    @Override
    public String toString() {
        return id_of_dishes_and_drinks + " " + type + " " + name + " " + expiration_date + " " + price;
    }
}
