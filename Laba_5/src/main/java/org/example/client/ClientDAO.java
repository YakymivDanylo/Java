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
    public ClientDAO( ClientDTO client) {
        this.client = client;
    }
    public ClientDAO(ClientDTO client,List<Order> list_orders){this.client = client;
    this.list_orders = list_orders;}

//    public set

}
