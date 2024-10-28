package org.example;

import com.mysql.cj.xdevapi.Client;
import org.example.admin.AdminDAO;
import org.example.admin.AdminDTO;
import org.example.client.ClientDAO;
import org.example.client.ClientDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.example.Menu.findClientsByEmployeeFrequency;

public class Main {
    public static void main(String[] args) throws Exception {
        String password = "123";
        String password2 = "234";
        String password1 = "123";
//        System.out.println(password2.hashCode());
////        AdminDTO adminDTO = new AdminDTO(1,"Danylo","surname","last_name","2005-12-26",password.hashCode());
////        AdminDAO adminDAO = new AdminDAO(adminDTO);
//        Authorizer authorizer = new Authorizer();
////       AdminDAO adminDAO1 = authorizer.adminAuthorization(adminDTO.getName(), adminDTO.getPassword());
//
//
//        ClientDTO clientDTO = new ClientDTO(1,"Vlad","smth","smth","2005-12-26",password2.hashCode(),"Burger");
//        ClientDAO clientDAO = authorizer.clientAuthorization(clientDTO.getName(),password2.hashCode());
//
//        Employee employee = new Employee(1,"Oleg","Mongol","Rostyslavovich","2006-06-04");
//
//
//        List<Dishes_and_drinks> dishesAndDrinks = List.of(new Dishes_and_drinks(4,"dish","Burger","2024-10-01",20));
//        List<Dishes_and_drinks> dishesAndDrinks1 = List.of(new Dishes_and_drinks(5,"drinks","mochito","2024-10-01",30));
//
//        Order order = new Order(1,employee,"2024-09-30",1,30.0,dishesAndDrinks);
////        Order order1 = new Order(2,employee,"2024-09-31",1,15.5,dishesAndDrinks1);
//
//        //clientDAO.cancelLastOrder(order);
//        clientDAO.makeOrder(order);
////        clientDAO.makeOrder(order1);
//        clientDAO.sortByOrderPrice();
//        clientDAO.showHistory();
//        Menu menu = new Menu() ;
//        Menu.sortByVisitFrequency(menu.getClients());


        Authorizer authorizer = new Authorizer();
//        String password = "123";
//        String password1 = "456";
        AdminDTO adminDTO = new AdminDTO(1,"Danylo","Yakymiv","Romanovich","2005-12-26",password.hashCode());
        AdminDAO admin = authorizer.adminAuthorization(adminDTO.getName(), adminDTO.getPassword());
//        AdminDTO adminDTO = new AdminDTO(2,"Matviy","Yakymiv","Romanovich","20011-12-26",password2.hashCode());
//        AdminDAO admin = authorizer.adminAuthorization(adminDTO.getName(), adminDTO.getPassword());
        System.out.println(password.hashCode());
//
//
        ClientDTO clientDTO = new ClientDTO(16,"Vova","Gorodetskiy","Vasylovich","2005-01-27",password2.hashCode(),"Makaronu");
        ClientDAO client = authorizer.clientAuthorization(clientDTO.getName(),clientDTO.getPassword());
//        ClientDTO clientDTO1 = new ClientDTO(17,"Matviy","Gorodetskiy","Vasylovich","2005-01-27",password2.hashCode(),"Beer");
//                ClientDAO client1 = authorizer.clientAuthorization(clientDTO1.getName(),password1.hashCode());
//
//
//        Employee employee1 = new Employee(2,"Rostyk","Rodshyk","Romanovich","2006-07-04");
//        Employee employee2 = new Employee(3,"Oleksandr ","Ivanovych ","Petrov ","1990-03-15");
//        Employee employee3 = new Employee(4,"Mariya","Stepanivna","Koval","1985-07-22");
//        Employee employee4 = new Employee(5,"Dmytro","Vasylovych","Smyrnov","1992-12-05");
//
//        List<Dishes_and_drinks> dishesAndDrinks1 = List.of(new Dishes_and_drinks(1,"dish","Burger","2024-10-01",20));
//        List<Dishes_and_drinks> dishesAndDrinks2 = List.of( new Dishes_and_drinks(2,"dish","makaronu","2024-10-07",75));
//        List<Dishes_and_drinks> dishesAndDrinks3 = List.of( new Dishes_and_drinks(3,"drinks","beer","none",30));
//        List<Dishes_and_drinks> dishesAndDrinks4 = List.of(new Dishes_and_drinks(4,"dish","beef","2024-10-01",300));
//        List<Dishes_and_drinks> dishesAndDrinks5 = List.of(new Dishes_and_drinks(5,"drinks","mochito","2024-10-01",50));
//        List<Dishes_and_drinks> dishesAndDrinks6 = List.of(new Dishes_and_drinks(6,"drinks","coca-cola","2024-10-01",20));
//
//
//        Order order1 = new Order(1, employee1,"2024-10-02",17,200.50,dishesAndDrinks1);
//        Order order2 = new Order(2, employee1,"2024-10-02",16,100.50,dishesAndDrinks2);
//        Order order3 = new Order(3, employee2,"2024-10-03",16,50.0,dishesAndDrinks3);
//        Order order4 = new Order(4, employee4,"2024-10-05",16,50.0,dishesAndDrinks4);
//        Order order4 = new Order(4, employee4,"2024-10-05",16,50.0,dishesAndDrinks4);

//        admin.addEmployee(employee1);
//        admin.addEmployee(employee2);
//        admin.addEmployee(employee3);
//        admin.addEmployee(employee4);

//        client.makeOrder(order1);
//        client.makeOrder(order2);
//        client.makeOrder(order3);
//        client1.makeOrder(order4);


        Menu menu = new Menu();
//        List<ClientDAO> clientDAOS = menu.getClients();
//        List<ClientDAO> sortedByVisiting =  Menu.sortByVisitFrequency(clientDAOS);/*1*/
//        for (ClientDAO clientDAO : sortedByVisiting) {
//            System.out.println(clientDAO);
//        }

//        List<ClientDAO> clients = findClientsByEmployeeFrequency(clientDAOS);/*2*/
//        List<ClientDAO> frequentClients = findClientsByEmployeeFrequency(clients);
//        frequentClients.forEach(client -> System.out.println(client.getClient().getName() + " " + client.getClient().getSurname()));

//        /*3*/
//        client.sortByOrderPrice();
//        for (Order order : client.getList_orders()) {
//            System.out.println(order);
//        }
//        /*4*/
//        Menu.sortByFavoriteDish(clientDAOS);
//        for (ClientDAO clientDAO : clientDAOS) {
//            System.out.println(clientDAO);
//        }

    }
}