package org.example;

import lombok.Getter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@Getter
public class Products {
    int id_product;
    String name_product;
    String expiration_date;

    public Products(int id_product, String name_product, String expiration_date) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.expiration_date = expiration_date;
    }
    static public Products productsFromDB(int id_product)  throws Exception {
        String configFile="D:\\java\\Laba_5\\src\\main\\resources\\config.properties";

        try(Connection connection = ConnectionDB.getConnection(configFile);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Products;")){
                while(resultSet.next()) {
                    if(resultSet.getInt(1)==id_product){
                        return new Products(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        );
                    }
                }
        }catch (SQLException e){
                System.out.println(e.getMessage());
                throw new Exception("Problem with connection to DB", e);
        }
        return null;
    }
    @Override
    public String toString() {
        return id_product + " " + name_product + " " + expiration_date;
    }
}
