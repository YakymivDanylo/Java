package org.example;

import org.example.client.ClientDAO;
import org.example.client.ClientDTO;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.client.ClientDAO.getOrderCountByEmployee;

public class Menu {
    //1
    public static List<ClientDAO> sortByVisitFrequency(List<ClientDAO> clients) {
        return clients.stream().sorted(Comparator.comparingInt(ClientDAO::getAmountOfOrders).reversed()).toList();
    }

    public List<ClientDAO> getClients() throws Exception {
        String configFilename = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";

        try (Connection connection = ConnectionDB.getConnection(configFilename);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from \"Cafe\".\"Client\";")) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<ClientDAO> clients = new ArrayList<ClientDAO>();
            while (resultSet.next()) {
                ClientDTO clientDTO = new ClientDTO(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7)
                );
                ClientDAO client = new ClientDAO(clientDTO);
                client.setOrderHistory();
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            System.out.println("Error! " + e.getMessage());
            throw new Exception("Problem with DB!", e);
        }
    }
    public static List<ClientDAO> findClientsByEmployeeFrequency(List<ClientDAO> clients) {
        return clients.stream()
                .filter(client -> client.getList_orders().size() > 1) // Фільтруємо клієнтів з більш ніж одним замовленням
                .filter(client -> {
                    // Перевіряємо, чи всі замовлення клієнта обслуговував один і той самий працівник
                    int firstEmployeeId = client.getList_orders().get(0).getEmployee().getId();
                    return client.getList_orders().stream()
                            .allMatch(order -> order.getEmployee().getId() == firstEmployeeId);
                })
                .collect(Collectors.toList());
    }

    public static void sortByFavoriteDish(List<ClientDAO> clients) {
        clients.sort(Comparator.comparing(client -> client.getClient().getFavorite_dish(),
                Comparator.nullsLast(Comparator.naturalOrder())));
    }

}
