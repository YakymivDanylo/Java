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
    int password;
    String favorite_dish;

    public ClientDTO(int id, String name, String surname, String last_name, String birth_date, int password, String favorite_dish) {
        super(id, name, surname, last_name, birth_date);
        this.password = password;
        this.favorite_dish = favorite_dish;
    }

    @Override
    public String toString() {
        return super.toString() + " " + password + " " + favorite_dish;
    }

}
