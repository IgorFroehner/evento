package com.ban.evento.repository;

import com.ban.evento.config.ConnectionSingleton;
import com.ban.evento.model.Autor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AutorRepository {

    private static AutorRepository instance = null;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement newId;
    private PreparedStatement delete;
    private PreparedStatement selectAll;
    private PreparedStatement findByArtigo;

    public AutorRepository() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionSingleton.getConnection();
        insert = connection.prepareStatement("INSERT INTO public.autores VALUES(?, ?, ?)");
        update = connection.prepareStatement("UPDATE public.autores SET nome=?, genero=? WHERE autorid=?");
        newId = connection.prepareStatement("SELECT max(autorid)+1 FROM public.autores");
        selectAll = connection.prepareStatement("SELECT * FROM public.autores");
        select = connection.prepareStatement("SELECT * FROM public.autores WHERE autorid=?");
        delete = connection.prepareStatement("DELETE FROM public.autores WHERE autorid=?");
        findByArtigo = connection.prepareStatement("SELECT a.* FROM autores a JOIN autoresartigo aa ON a.autorid = aa.autorid WHERE aa.artigoid=?");
    }

    public static AutorRepository getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new AutorRepository();
        }
        return instance;
    }

    public Optional<Autor> findById(Integer artigoid) throws SQLException {
        select.setInt(1, artigoid);
        ResultSet rs = select.executeQuery();
        if (rs.next())
            return Optional.of(new Autor(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3).charAt(0)
            ));
        return Optional.empty();
    }

    public void update(Autor autor) throws SQLException {
        update.setString(1, autor.getNome());
        update.setString(2, autor.getGenero()+"");
        update.setInt(3, autor.getAutorid());
        update.executeUpdate();
    }

    public void save(Autor autor) throws SQLException {
        this.insert.setInt(1, selectNewId());
        this.insert.setString(2, autor.getNome());
        this.insert.setString(3, autor.getGenero()+"");
        this.insert.executeUpdate();
    }

    public Integer selectNewId() throws SQLException {
        ResultSet rs = newId.executeQuery();
        if (rs.next())
            return rs.getInt(1);
        return -1;
    }

    public List<Autor> findAll() throws SQLException {
        List<Autor> list = new ArrayList<>();
        ResultSet rs = selectAll.executeQuery();
        while (rs.next()) {
            list.add(new Autor(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3).charAt(0)
            ));
        }
        return list;
    }

    public void delete(Autor autor) throws SQLException {
        delete.setInt(1, autor.getAutorid());
        delete.executeUpdate();
    }

    public List<Autor> findByArtigoId(Integer artigoid) throws SQLException {
        findByArtigo.setInt(1, artigoid);
        ResultSet rs = findByArtigo.executeQuery();
        List<Autor> res = new ArrayList<>();
        while (rs.next()) {
            res.add(new Autor(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3).charAt(0)
            ));
        }
        return res;
    }

}
