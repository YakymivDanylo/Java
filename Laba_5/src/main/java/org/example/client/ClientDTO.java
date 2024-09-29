package org.example.client;

import lombok.Getter;
import org.example.ConnectionDB;
import org.example.Order;
import org.example.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class ClientDTO extends Person {
    String password;
    String favoriteDish;
    List<Order> orders = new ArrayList<>();

    public ClientDTO(int id, String name, String surname, String last_name, String birth_date, String password, String favoriteDish) {
        super(id, name, surname, last_name, birth_date);
        this.password = password;
        this.favoriteDish = favoriteDish;
    }

    public static void sortByVisitFrequency(List<ClientDTO> clients) {
        clients.sort(Comparator.comparingInt(client -> client.getOrders().size()));
    }

    public static void sortByEmployeeFrequency(List<ClientDTO> clients) {
        clients.sort((client1, client2) -> {

            Map<Integer, Long> employeeCounts1 = client1.getOrders().stream()
                    .collect(Collectors.groupingBy(order -> order.getEmployee().getId(), Collectors.counting()));
            Map<Integer, Long> employeeCounts2 = client2.getOrders().stream()
                    .collect(Collectors.groupingBy(order -> order.getEmployee().getId(), Collectors.counting()));
            Optional<Long> maxCount1 = employeeCounts1.values().stream().max(Long::compareTo);
            Optional<Long> maxCount2 = employeeCounts2.values().stream().max(Long::compareTo);

            return maxCount2.orElse(0L).compareTo(maxCount1.orElse(0L));
        });
    }
    public void sortByOrderPrice() {
        this.orders.sort(Comparator.comparingDouble(Order::getTotal_price));
    }

    public static void sortByFavoriteDish(List<ClientDTO> clients) {
        clients.sort(Comparator.comparing(client -> client.getFavoriteDish(),
                Comparator.nullsLast(Comparator.naturalOrder())));
    }

}
