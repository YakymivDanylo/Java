package org.example.client;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lombok.Getter;
import org.example.ConnectionDB;
import org.example.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDTO extends Person {
    String password;
    List<MysqlxCrud.Order> orders = new ArrayList<>();

    public ClientDTO(int id, String name, String surname,String last_name, String birth_date, String password) {
        super(id, name, surname, last_name, birth_date);
        this.password = password;
    }
}
