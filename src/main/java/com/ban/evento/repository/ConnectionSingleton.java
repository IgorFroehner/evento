package com.ban.evento.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    public static Connection connection = null;
    public static String senha = "1234";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection==null) {
            String url = "jdbc:postgresql://localhost:5432/evento";
            String user = "igor";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, senha);
        }
        return connection;
    }

}
