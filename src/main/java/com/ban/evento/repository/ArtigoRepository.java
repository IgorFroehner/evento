package com.ban.evento.repository;

import com.ban.evento.model.artigo.Artigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtigoRepository {


    private static ArtigoRepository instance = null;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement delete;
    private PreparedStatement selectAll;

    public ArtigoRepository() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionSingleton.getConnection();
        insert = connection.prepareStatement("INSERT INTO artigos VALUES(?, ?, ?, ?)");
        selectAll = connection.prepareStatement("SELECT * FROM artigos");
    }

    public static ArtigoRepository getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new ArtigoRepository();
        }
        return instance;
    }

    public void save(Artigo artigo) {
    }

    public List<Artigo> findAll() throws SQLException {
        List<Artigo> list = new ArrayList<>();
        ResultSet rs = selectAll.executeQuery();
        while (rs.next()) {
            list.add(new Artigo(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4)
            ));
        }
        return list;
    }


}
