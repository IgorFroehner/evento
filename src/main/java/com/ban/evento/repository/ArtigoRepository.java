package com.ban.evento.repository;

import com.ban.evento.model.Artigo;

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
    private PreparedStatement newId;
    private PreparedStatement delete;
    private PreparedStatement selectAll;

    public ArtigoRepository() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionSingleton.getConnection();
        insert = connection.prepareStatement("INSERT INTO public.artigos VALUES(?, ?, ?, ?)");
        update = connection.prepareStatement("UPDATE public.artigos SET titulo=?, tipoid=?, edicaoid=? WHERE artigoid=?");
        newId = connection.prepareStatement("SELECT max(artigoid)+1 FROM public.artigos");
        selectAll = connection.prepareStatement("SELECT * FROM public.artigos");
    }

    public static ArtigoRepository getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new ArtigoRepository();
        }
        return instance;
    }

    public void save(Artigo artigo) throws SQLException {
        this.insert.setInt(1, selectNewId());
        this.insert.setString(2, artigo.getTitulo());
        this.insert.setInt(3, artigo.getTipoid());
        this.insert.setInt(4, artigo.getEdicaoid());
        this.insert.executeUpdate();
    }

    public Integer selectNewId() throws SQLException {
        ResultSet rs = newId.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
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
