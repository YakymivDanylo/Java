package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee extends Person {


    public Employee(int id, String name, String surname, String last_name, String birth_date) {
        super(id, name, surname, last_name, birth_date);
    }

    static public Employee employeeFromDB(int id) throws SQLException {
        String configFile="D:\\java\\Laba_5\\src\\main\\resources\\config.properties";
        try(Connection connection = ConnectionDB.getConnection(configFile);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Cafe\".\"Employee\";")){
            while(resultSet.next()) {
                if(resultSet.getInt(1) == id) {
                    return new Employee(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException("Problem with connection to DB", e);
        }
        return null;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
