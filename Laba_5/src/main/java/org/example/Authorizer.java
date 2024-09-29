package org.example;

import org.example.admin.*;
import org.example.client.*;
import java.sql.*;

public class Authorizer {


    public ClientDAO register(ClientDTO client) {
        String configFile = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectionDB.getConnection(configFile);
            String query = "INSERT INTO client(name_client,surname_client,last_name_client, birth_date_client, password_client,favoriteDish)" +
                    " VALUES (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            try {
                preparedStatement.setString(1, client.getName());
                preparedStatement.setString(2, client.getSurname());
                preparedStatement.setString(3, client.getLast_name());
                preparedStatement.setString(4, client.getBirth_date());
                preparedStatement.setString(5, client.getPassword());
                preparedStatement.setString(6, client.getFavoriteDish());


                int result = preparedStatement.executeUpdate();
                System.out.println("Registration is done " + result);

                return new ClientDAO(client);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public AdminDAO adminAuthorization(String name, String password) throws Exception {
        String configFilename = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";

        try (Connection connection = ConnectionDB.getConnection(configFilename);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Admin;")) {

            while (resultSet.next()) {

                if (resultSet.getString(2).equals(name) && resultSet.getString(6).equals(password)) {
                    AdminDTO admin = new AdminDTO(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    );
                    System.out.println("You have authorized as ADMIN");
                    ResultSetMetaData rsMetaData = resultSet.getMetaData();

                    System.out.println();
                    System.out.println("Some sensitive information about each Admin`s data:");
                    int columnCount = rsMetaData.getColumnCount();
                    System.out.println("Number of values: " + columnCount);

                    for (int i = 1; i <= columnCount; i++) {
                        System.out.println(rsMetaData.getColumnName(i) + " (" + rsMetaData.getColumnTypeName(i)+")");
                    }
                    return new AdminDAO(admin);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error! " + e.getMessage());
            throw new Exception("Problem with DB!", e);
        }
        return null;
    }

    public ClientDAO clientAuthorization(String name, String password) throws Exception {
        String configFilename = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";

        try (Connection connection = ConnectionDB.getConnection(configFilename);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Client;")) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {

                if (resultSet.getString(2).equals(name) && resultSet.getString(7).equals(password)) {
                    ClientDTO client = new ClientDTO(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    );
                    ClientDAO clientDao = new ClientDAO(client);
                    return clientDao;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error! " + e.getMessage());
            throw new Exception("Problem with DB!", e);
        }
        return null;
    }

}