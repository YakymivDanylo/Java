package org.example;

import org.example.admin.AdminDAO;
import org.example.admin.AdminDTO;
import org.example.client.ClientDAO;
import org.example.client.ClientDTO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        AdminDTO adminDTO = new AdminDTO(1,"name","surname","last_name","2005-12-26","123");
        AdminDAO adminDAO = new AdminDAO(adminDTO);
        Dishes_and_drinks dishesAndDrinks = new Dishes_and_drinks(1,"drinks","mochito","2024-09-30",55);
        Products products = new Products(1,"milk","2024-09-29");
        adminDAO.addProducts(products);
        adminDAO.addDishesDrinks(dishesAndDrinks);

        ClientDTO clientDTO = new ClientDTO(1,"Danylo","Yakymiv","Romanovich","2005-12-26","123","Burger");
        Authorizer authorizer = new Authorizer();


    }
}