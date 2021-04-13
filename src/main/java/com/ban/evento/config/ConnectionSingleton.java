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
            String url = "jdbc:postgresql://ec2-54-145-249-177.compute-1.amazonaws.com:5432/d4k6odkaig1qpd";
            String senha = "2d21dfa0e3f4ee94efc72ebe85175eaae2c5d114dbe5044f825e0d9a4b4a6598";
            String user = "oqkmckssxriydb";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, senha);
        }
        return connection;
    }

}
