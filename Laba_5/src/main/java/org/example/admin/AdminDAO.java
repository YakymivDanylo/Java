package org.example.admin;

import lombok.*;
import org.example.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Getter
@Setter
public class AdminDAO {
    AdminDTO admin;

    public AdminDAO() {}
    public AdminDAO(AdminDTO admin) {this.admin = admin;}

    public void addProducts(Products product){
        String configFile = "D:\\java\\Laba_5\\config.properties";
        try{
            Connection connection = ConnectionDB.getConnection(configFile);
            String query = "INSERT INTO Products(name_product,expiration_date) VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try{
                preparedStatement.setString(1, product.getName_product());
                preparedStatement.setString(2, product.getExpiration_date());

                int result = preparedStatement.executeUpdate();
                System.out.println("Rows affected" + result);
                preparedStatement.close();
                connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }finally {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEmployee(Employee employee){
        String configFile = "D:\\java\\Laba_5\\config.properties";
        try{
                Connection connection = ConnectionDB.getConnection(configFile);
                String query = "INSERT INTO Employee (name_employee,surname_employee,last_name_employee, birth_date_employee) VALUES (?,?,?,?)";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                try{
                    preparedStatement.setString(1,employee.getName());
                    preparedStatement.setString(2,employee.getSurname());
                    preparedStatement.setString(3,employee.getLast_name());
                    preparedStatement.setString(4,employee.getBirth_date());

                    int result = preparedStatement.executeUpdate();
                    System.out.println("Rows affected" + result);
                    preparedStatement.close();
                    connection.close();
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                finally {
                    connection.close();
                }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addDishesDrinks(Dishes_and_drinks listOfDishesAndDrinks){
        String configFile = "D:\\java\\Laba_5\\config.properties";
        try{
            Connection connection = ConnectionDB.getConnection(configFile);
            String query = "INSERT INTO List_of_dishes_and_drinks(type_of_dishes_and_drinks,name_of_dishes_and_drinks,expiration_date_of_dishes_and_drinks,price) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            try{
                preparedStatement.setString(1,listOfDishesAndDrinks.getType());
                preparedStatement.setString(2,listOfDishesAndDrinks.getName());
                preparedStatement.setString(3,listOfDishesAndDrinks.getExpiration_date());
                preparedStatement.setDouble(4,listOfDishesAndDrinks.getPrice());

                int result = preparedStatement.executeUpdate();
                System.out.println("Rows affected" + result);
                preparedStatement.close();
                connection.close();
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }finally {
                connection.close();
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
