package org.example;

import org.example.admin.AdminDAO;
import org.example.admin.AdminDTO;
import org.example.client.ClientDAO;
import org.example.client.ClientDTO;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String password = "123";
        String password2 = "234";
//        AdminDTO adminDTO = new AdminDTO(1,"Danylo","surname","last_name","2005-12-26",password.hashCode());
//        AdminDAO adminDAO = new AdminDAO(adminDTO);
        Authorizer authorizer = new Authorizer();
//        try {
//            AdminDAO adminDAO1 = authorizer.adminAuthorization(adminDTO.getName(), adminDTO.getPassword());
//        }catch (SQLException e){
//            e.printStackTrace();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        ClientDTO clientDTO = new ClientDTO(1,"Vlad","smth","smth","2005-12-26",password2.hashCode(),"Burger");
        ClientDAO clientDAO = authorizer.clientAuthorization(clientDTO.getName(),password2.hashCode());

        Employee employee = new Employee(1,"Oleg","Mongol","Rostyslavovich","2006-06-04");
//
        Order order = new Order(1,employee,"2024-09-30",1,30.0);
        Order order1 = new Order(2,employee,"2024-09-30",1,15.5);
        List<Dishes_and_drinks> dishesAndDrinks = List.of(new Dishes_and_drinks(1,"dish","Burger","2024-10-01",20,order));
        List<Dishes_and_drinks> dishesAndDrinks1 = List.of(new Dishes_and_drinks(2,"drinks","mochito","2024-10-01",30,order1));

        //clientDAO.cancelLastOrder(order);
        clientDAO.makeOrder(order);
        clientDAO.makeOrder(order1);
        clientDAO.sortByOrderPrice();
        clientDAO.showHistory();
        Menu menu = new Menu() ;
        Menu.sortByVisitFrequency(menu.getClients());
    }
}