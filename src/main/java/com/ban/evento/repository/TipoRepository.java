package com.ban.evento.repository;

import com.ban.evento.config.ConnectionSingleton;
import com.ban.evento.model.Tipo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TipoRepository {

    private static TipoRepository instance = null;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement newId;
    private PreparedStatement delete;
    private PreparedStatement selectAll;
    private PreparedStatement nroArtigosFromTipo;

    public TipoRepository() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionSingleton.getConnection();
        insert = connection.prepareStatement("INSERT INTO public.tipos VALUES(?, ?)");
        update = connection.prepareStatement("UPDATE public.tipos SET nome=? WHERE tipoid=?");
        newId = connection.prepareStatement("SELECT max(tipoid)+1 FROM public.tipos");
        selectAll = connection.prepareStatement("SELECT * FROM public.tipos ORDER BY tipoid");
        select = connection.prepareStatement("SELECT * FROM public.tipos WHERE tipoid=?");
        delete = connection.prepareStatement("DELETE FROM public.tipos WHERE tipoid=?");
        nroArtigosFromTipo = connection.prepareStatement("select a.cont from tipos t join (select tipoid as id, count(*) as cont from artigos where tipoid=? group by tipoid) a on t.tipoid=a.id");
    }

    public static TipoRepository getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new TipoRepository();
        }
        return instance;
    }

    public Optional<Tipo> findById(Integer tipoid) throws SQLException {
        select.setInt(1, tipoid);
        ResultSet rs = select.executeQuery();
        if (rs.next())
            return Optional.of(new Tipo(
                    rs.getInt(1),
                    rs.getString(2)
            ));
        return Optional.empty();
    }

    public void update(Tipo tipo) throws SQLException {
        update.setString(1, tipo.getNome());
        update.setInt(2, tipo.getTipoid());
        update.executeUpdate();
    }

    public void save(Tipo tipo) throws SQLException {
        this.insert.setInt(1, selectNewId());
        this.insert.setString(2, tipo.getNome());
        this.insert.executeUpdate();
    }

    public Integer selectNewId() throws SQLException {
        ResultSet rs = newId.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }

    public List<Tipo> findAll() throws SQLException {
        List<Tipo> list = new ArrayList<>();
        ResultSet rs = selectAll.executeQuery();
        while (rs.next()) {
            list.add(new Tipo(
                    rs.getInt(1),
                    rs.getString(2)
            ));
        }
        return list;
    }

    public Integer nroArtigosFromTipo(Integer tipoId) throws SQLException {
        nroArtigosFromTipo.setInt(1, tipoId);
        ResultSet rs = nroArtigosFromTipo.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return 0;
    }

    public void delete(Tipo tipo) throws SQLException {
        delete.setInt(1, tipo.getTipoid());
        delete.executeUpdate();
    }

}
