package org.example.admin;

import lombok.Getter;
import org.example.*;

import java.util.Properties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Getter
public class AdminDAO {
    AdminDTO admin;
    public AdminDAO() {}
    public AdminDAO(AdminDTO admin) {this.admin = admin;}
}
