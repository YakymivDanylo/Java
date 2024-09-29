package org.example.client;

import lombok.Getter;
import org.example.ConnectionDB;
import org.example.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    @Getter
    ClientDTO client;
    List<Order> list_orders;

    public ClientDAO(ClientDTO client) {
        this.client = client;
    }

    public ClientDAO(ClientDTO client, List<Order> list_orders) {
        this.client = client;
        this.list_orders = list_orders;
    }

    public void makeOrder(Order order) {
        list_orders.add(order);
        String configFile = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectionDB.getConnection(configFile);
            for (Dishes_and_drinks dishesAndDrinks : order.getDishesAndDrinks()) {
                String query = "insert into \"Cafe\".\"Order\" (Employee_id_employee,Dishes_and_Drinks_id,data_order) values(?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                try {
                    preparedStatement.setInt(1, order.getEmployee().getId());
                    preparedStatement.setInt(2, dishesAndDrinks.getId_dishes_drinks());
                    preparedStatement.setString(3,order.getData_order());

                    int result = preparedStatement.executeUpdate();
                    System.out.println("Rows affected" + result);


                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } finally {
                    preparedStatement.close();
                    connection.close();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showHistory(){
        for(Order order : list_orders){
            System.out.println(order);
        }
    }

}
