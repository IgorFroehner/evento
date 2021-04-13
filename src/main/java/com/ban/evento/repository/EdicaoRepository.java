package com.ban.evento.repository;

import com.ban.evento.config.ConnectionSingleton;
import com.ban.evento.model.Edicao;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EdicaoRepository {

    private static EdicaoRepository instance = null;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement newId;
    private PreparedStatement delete;
    private PreparedStatement selectAll;

    public EdicaoRepository() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionSingleton.getConnection();
        insert = connection.prepareStatement("INSERT INTO public.edicoes VALUES(?, ?, ?, ?, ?)");
        update = connection.prepareStatement("UPDATE public.edicoes SET cidade=?, uf=?, qtdparticipantes=?, ano=? WHERE edicaoid=?");
        newId = connection.prepareStatement("SELECT max(edicaoid)+1 FROM public.edicoes");
        selectAll = connection.prepareStatement("SELECT * FROM public.edicoes ORDER BY edicaoid");
        select = connection.prepareStatement("SELECT * FROM public.edicoes WHERE edicaoid=?");
        delete = connection.prepareStatement("DELETE FROM public.edicoes WHERE edicaoid=?");
    }

    public static EdicaoRepository getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new EdicaoRepository();
        }
        return instance;
    }

    public Optional<Edicao> findById(Integer edicaoid) throws SQLException {
        select.setInt(1, edicaoid);
        ResultSet rs = select.executeQuery();
        if (rs.next())
            return Optional.of(new Edicao(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
            ));
        return Optional.empty();
    }

    public void update(Edicao edicao) throws SQLException {
        update.setString(1, edicao.getCidade());
        update.setString(2, edicao.getUf());
        update.setInt(3, edicao.getQtdparticipantes());
        update.setInt(4, edicao.getAno());
        update.setInt(5, edicao.getEdicaoid());
        update.executeUpdate();
    }

    public void save(Edicao edicao) throws SQLException {
        this.insert.setInt(1, selectNewId());
        this.insert.setString(2, edicao.getCidade());
        this.insert.setString(3, edicao.getUf());
        this.insert.setInt(4, edicao.getQtdparticipantes());
        this.insert.setInt(5, edicao.getAno());
        this.insert.executeUpdate();
    }

    public Integer selectNewId() throws SQLException {
        ResultSet rs = newId.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }

    public List<Edicao> findAll() throws SQLException {
        List<Edicao> list = new ArrayList<>();
        ResultSet rs = selectAll.executeQuery();
        while (rs.next()) {
            list.add(new Edicao(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5)
            ));
        }
        return list;
    }

    public void delete(Edicao edicao) throws SQLException {
        delete.setInt(1, edicao.getEdicaoid());
        delete.executeUpdate();
    }

}
