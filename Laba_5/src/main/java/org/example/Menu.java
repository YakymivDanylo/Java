package org.example;

import org.example.client.ClientDAO;
import org.example.client.ClientDTO;

import java.util.Comparator;
import java.util.List;

public class Menu {
    public static void sortByVisitFrequency(List<ClientDAO> clients) {
        clients.sort(Comparator.comparingInt(client -> client.getList_orders().size()));
    }

}
