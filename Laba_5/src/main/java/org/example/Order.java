package org.example;

import lombok.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Getter
@Setter
public class Order {
    int id;
    Employee employee;
    List<Dishes_and_drinks> dishesAndDrinks;
    String data_order;
    int client_id_client;
    Double total_price;
    public Order(int id,Employee employee,List<Dishes_and_drinks> dishesAndDrinks,String data_order,int client_id_client,Double total_price) {
        this.id = id;
        this.employee = employee;
        this.dishesAndDrinks = dishesAndDrinks;
        this.data_order = data_order;
        this.client_id_client = client_id_client;
        this.total_price = total_price;


    }

    @Override
    public String toString() {
        return employee.toString() + " " + dishesAndDrinks.toString() + " " + data_order + " "+ client_id_client+" "+total_price ;
    }

    public Employee getEmployee(){return employee;}

}
