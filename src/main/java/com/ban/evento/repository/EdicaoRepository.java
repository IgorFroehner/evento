package com.ban.evento.repository;

import com.ban.evento.model.Edicao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EdicaoRepository {

    private static EdicaoRepository instance = null;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement delete;
    private PreparedStatement selectAll;

    public EdicaoRepository() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionSingleton.getConnection();

    }



}
