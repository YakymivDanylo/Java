package org.example;

import lombok.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    static public List<Dishes_and_drinks> dishFromDB(int id_order) throws Exception{
        String configFile = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";
        List<Dishes_and_drinks> dishesAndDrinks = new ArrayList<>();
        try(Connection connection = ConnectionDB.getConnection(configFile);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Cafe\".\"List_of_dishes_and_drinks\";")){
            while(resultSet.next()) {
                if(resultSet.getInt(6)==id_order) {
                    dishesAndDrinks.add(new Dishes_and_drinks(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDouble(5)
//                            resultSet.getInt(6)
                    ))  ;
                }
            }
            return dishesAndDrinks;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new Exception("Problem with connection to DB", e);
        }
    }


    @Override
    public String toString() {
        return id_of_dishes_and_drinks + " " + type + " " + name + " " + expiration_date + " " + price;
    }
}
