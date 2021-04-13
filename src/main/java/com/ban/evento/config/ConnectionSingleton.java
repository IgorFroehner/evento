package com.ban.evento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Configuration
public class ConnectionSingleton {

    public static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection==null) {
            String url = "jdbc:postgresql://localhost:5432/evento";
            String senha = "1234";
            String user = "igor";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, senha);
        }
        return connection;
    }

}
