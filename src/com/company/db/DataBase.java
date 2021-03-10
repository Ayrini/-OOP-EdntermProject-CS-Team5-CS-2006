package com.company.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase implements INTdatabase {
    public void createTables(Connection connection) {
        try {

            String sql = "CREATE TABLE customers(\n" +
                    "id SERIAL,\n" +
                    "first_name text,\n" +
                    "last_name text,\n" +
                    "age int,\n" +
                    "gender int,\n" +
                    "money double precision,\n" +
                    "PRIMARY KEY (id));\n" +
                    "\n" +
                    "CREATE TABLE workers(\n" +
                    "id SERIAL,\n" +
                    "first_name text,\n" +
                    "last_name text,\n" +
                    "position text,\n" +
                    "age int,\n" +
                    "gender int,\n" +
                    "salary int,\n" +
                    "PRIMARY KEY (id));";

            PreparedStatement statement1 = connection.prepareStatement(sql);

            statement1.executeUpdate();
            System.out.println("Tables have been created");

        } catch (
                SQLException e) {
            System.out.println("Error in creating table");
            e.printStackTrace();
        }
    }

    public void deleteTables(Connection connection) throws SQLException {
        System.out.println("Connected to server");

        String sql = "DROP TABLE Customers;" +
                    "DROP TABLE Workers";

        PreparedStatement statement1 = connection.prepareStatement(sql);

        statement1.executeUpdate();
        System.out.println("Tables have been deleted");

        }

}

