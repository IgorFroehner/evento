package com.ban.evento.repository;

import com.ban.evento.model.artigo.Artigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArtigoRepository {


    private static ArtigoRepository instance = null;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement delete;


    public ArtigoRepository() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionSingleton.getConnection();
        insert = connection.prepareStatement("INSERT INTO artigos VALUES(?, ?, ?, ?)");
    }

    public static ArtigoRepository getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new ArtigoRepository();
        }
        return instance;
    }

    public void save(Artigo artigo) {
    }


}
