package org.example.client;

import lombok.Getter;
import org.example.ConnectionDB;
import org.example.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    ClientDTO client;
    public ClientDAO(ClientDTO client){this.client = client;}

    public ClientDTO getClient() {
        return client;
    }
}
