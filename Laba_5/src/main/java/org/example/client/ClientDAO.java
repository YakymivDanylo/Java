package org.example.client;

import lombok.Getter;
import org.example.ConnectionDB;
import org.example.*;
import org.example.admin.AdminDAO;


import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class ClientDAO {
    ClientDTO client;
    List<Order> list_orders = new ArrayList<>();

    public ClientDAO(ClientDTO client) {
        this.client = client;
    }

    public ClientDAO(ClientDTO client, List<Order> list_orders) {
        this.client = client;
        this.list_orders = list_orders;
    }

    public void makeOrder(Order order) {
        list_orders.add(order);
        String configFile = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectionDB.getConnection(configFile);

            String queryOrder = "INSERT INTO \"Cafe\".\"Order\" (employee_id_employee,data_order,client_id_client,total_sum) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(queryOrder, Statement.RETURN_GENERATED_KEYS);

            try {
                preparedStatement.setInt(1, order.getEmployee().getId());
                preparedStatement.setString(2, order.getData_order());
                preparedStatement.setInt(3, order.getClient_id_client());
                preparedStatement.setDouble(4, order.getTotal_sum());

                int result = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + result);

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);

                    for (Dishes_and_drinks diAndDri : order.getDiAndDi()) {
                        AdminDAO.addDishesDrinks(diAndDri, orderId);
                    }
                } else {
                    throw new SQLException("Не вдалося отримати order_id.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showHistory() {
        for (Order order : list_orders) {
            System.out.println(order);
        }
    }

    public void setOrderHistory() {
        String configFilename = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";
        List<Order> orders = new ArrayList<>();
        try {
            Connection connection = ConnectionDB.getConnection(configFilename);
            Statement statement = connection.createStatement();

            try (connection; statement; ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Cafe\".\"Order\" ;")) {
                while (resultSet.next()) {
                    if (resultSet.getInt(4) == this.getClient().getId()) {
                        Order order = new Order(resultSet.getInt(1), Employee.employeeFromDB(resultSet.getInt(2)), resultSet.getString(3), this.getClient().getId(), resultSet.getDouble(5), Dishes_and_drinks.dishFromDB(resultSet.getInt(1)));
                        orders.add(order);
                    }
                }
                this.list_orders = orders;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void cancelLastOrder(Order order) {
        String configFile = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectionDB.getConnection(configFile);
            String query = "delete from \"Cafe\".\"Order\" where employee_id_employee = ? AND client_id_client = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            try {
                preparedStatement.setInt(1, order.getEmployee().getId());
                preparedStatement.setInt(2, order.getClient_id_client());
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    list_orders.remove(order); // Видаляємо замовлення з історії
                    System.out.println("Останнє замовлення успішно скасовано.");
                } else {
                    System.out.println("Не вдалося скасувати останнє замовлення.");
                }

                System.out.println("Rows affected: " + result);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateOrder(Order order, Employee employee) {
        String configFile = "D:\\java\\Laba_5\\src\\main\\resources\\config.properties";
        try {
            Connection connection = ConnectionDB.getConnection(configFile);
            String query = "update \"Cafe\".\"Order\" set employee_id_employee = ? where client_id_client = ?,id_order = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try {
                preparedStatement.setInt(1, employee.getId());
                preparedStatement.setInt(2, order.getClient_id_client());
                preparedStatement.setInt(3, order.getId());
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    list_orders.remove(order);
                    order.setEmployee(employee);
                    list_orders.add(order);
                    System.out.println("Останнє замовлення успішно змінено.");
                } else {
                    System.out.println("Не вдалося скасувати останнє замовлення.");
                }
                System.out.println("Rows affected: " + result);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                preparedStatement.close();
                connection.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Map<Integer, Long> getOrderCountByEmployee(ClientDAO client) {
        return client.getList_orders().stream()
                .filter(order -> order.getEmployee() != null) // Виключаємо замовлення без працівника
                .collect(Collectors.groupingBy(
                        order -> order.getEmployee().getId(), // Групуємо за ID працівника
                        Collectors.counting() // Рахуємо кількість замовлень
                ));
    }


    //3
    public void sortByOrderPrice() {
        this.list_orders.sort(Comparator.comparingDouble(Order::getTotal_sum).reversed());
    }

    //4


    @Override
    public String toString() {
        return client.toString();
    }

    public int getAmountOfOrders(){
        return this.list_orders.size();
    }
}


