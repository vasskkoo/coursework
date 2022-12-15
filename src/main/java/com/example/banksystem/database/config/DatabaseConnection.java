package com.example.banksystem.database.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bank_db?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    static Connection connection;

    public static Connection getConnection() throws SQLException {

        connection = DriverManager.getConnection(String.format(URL), USER, PASSWORD);

        return connection;

    }

}
