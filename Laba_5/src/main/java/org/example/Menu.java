package org.example;

import org.example.client.ClientDAO;
import org.example.client.ClientDTO;

import java.sql.*;
import java.util.*;

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
    public static void sortByEmployeeFrequency(List<ClientDAO> clients) {
        clients.sort((client1, client2) -> {
            // Отримуємо мапи "працівник - кількість замовлень" для кожного клієнта
            Map<Integer, Long> employeeOrderCount1 = getOrderCountByEmployee(client1);
            Map<Integer, Long> employeeOrderCount2 = getOrderCountByEmployee(client2);

            // Знаходимо перетин ключів (ID працівників) для обох мап
            Set<Integer> commonEmployeeIds = new HashSet<>(employeeOrderCount1.keySet());
            commonEmployeeIds.retainAll(employeeOrderCount2.keySet());

            // Якщо немає спільних працівників, порівнюємо за максимальним значенням, як раніше
            if (commonEmployeeIds.isEmpty()) {
                Optional<Long> maxOrdersClient1 = employeeOrderCount1.values().stream().max(Long::compareTo);
                Optional<Long> maxOrdersClient2 = employeeOrderCount2.values().stream().max(Long::compareTo);
                return maxOrdersClient2.orElse(0L).compareTo(maxOrdersClient1.orElse(0L));
            } else {
                // Якщо є спільні працівники, порівнюємо за максимальним значенням для спільних працівників
                Optional<Long> maxCommonOrdersClient1 = commonEmployeeIds.stream()
                        .map(employeeId -> employeeOrderCount1.getOrDefault(employeeId, 0L))
                        .max(Long::compareTo);
                Optional<Long> maxCommonOrdersClient2 = commonEmployeeIds.stream()
                        .map(employeeId -> employeeOrderCount2.getOrDefault(employeeId, 0L))
                        .max(Long::compareTo);
                return maxCommonOrdersClient2.orElse(0L).compareTo(maxCommonOrdersClient1.orElse(0L));
            }
        });
    }

}
