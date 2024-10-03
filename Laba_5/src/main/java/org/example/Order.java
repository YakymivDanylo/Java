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
    List<Dishes_and_drinks> diAndDi;
    public Order(int id,Employee employee,String data_order,int client_id_client,Double total_sum , List<Dishes_and_drinks> diAndDi) {
        this.id = id;
        this.employee = employee;
        this.data_order = data_order;
        this.client_id_client = client_id_client;
        this.total_sum = total_sum;
        this.diAndDi = diAndDi;


    }

    public void setDishes() throws Exception{
        this.diAndDi =  Dishes_and_drinks.dishFromDB(this.id);
    }

    @Override
    public String toString() {
        return employee.toString() + " " + data_order + " "+ client_id_client+" "+total_sum ;
    }


}
