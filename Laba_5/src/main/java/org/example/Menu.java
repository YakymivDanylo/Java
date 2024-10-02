package org.example;

import org.example.client.ClientDAO;
import org.example.client.ClientDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Menu {
    public static void sortByVisitFrequency(List<ClientDAO> clients) {
        clients.sort(Comparator.comparingInt(client -> client.getList_orders().size()));
    }

    public List<ClientDAO> getClients() throws Exception {
        String configFilename = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";

        try (Connection connection = ConnectionDB.getConnection(configFilename);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from \"Cafe\".\"Client\";")) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
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
                clients.add(client);
                System.out.println("Successful authorization as CLIENT");
                return clients;
            }
        } catch (SQLException e) {
            System.out.println("Error! " + e.getMessage());
            throw new Exception("Problem with DB!", e);
        }
        return null;
    }


}
