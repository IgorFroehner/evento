package com.ban.evento.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoresArtigoRepository {

    private static AutoresArtigoRepository instance;
    private PreparedStatement save;
    private PreparedStatement newId;

    private AutoresArtigoRepository() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionSingleton.getConnection();
        save = connection.prepareStatement("INSERT INTO autoresartigo VALUES (?, ?, ?)");
        newId = connection.prepareStatement("SELECT max(id)+1 FROM public.autoresartigo");
    }

    public static AutoresArtigoRepository getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new AutoresArtigoRepository();
        }
        return instance;
    }

    public void save(Integer autorid, Integer artigoid) throws SQLException {
        save.setInt(1, selectNewId());
        save.setInt(2, autorid);
        save.setInt(3, artigoid);
    }

    public Integer selectNewId() throws SQLException {
        ResultSet rs = newId.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }

}
