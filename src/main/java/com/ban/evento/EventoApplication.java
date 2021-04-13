package com.ban.evento;

import com.ban.evento.config.ConnectionSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class EventoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventoApplication.class, args);

		try {
			Connection conn = ConnectionSingleton.getConnection();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
