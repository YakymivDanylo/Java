package org.example;

import lombok.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    int id;
    Employee employee;
    String data_order;
    int client_id_client;
    Double total_sum;
    public Order(int id,Employee employee,String data_order,int client_id_client,Double total_sum) {
        this.id = id;
        this.employee = employee;
        this.data_order = data_order;
        this.client_id_client = client_id_client;
        this.total_sum = total_sum;


    }

    public List<Dishes_and_drinks> dishesFromDB() throws Exception{
        List<Dishes_and_drinks> dishes = new ArrayList<>();
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
        return employee.toString() + " " + data_order + " "+ client_id_client+" "+total_sum ;
    }


}
