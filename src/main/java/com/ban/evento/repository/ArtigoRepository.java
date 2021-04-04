package com.ban.evento.repository;

import com.ban.evento.model.Artigo;
import com.ban.evento.model.Edicao;
import com.ban.evento.model.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArtigoRepository {

    private static ArtigoRepository instance = null;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement newId;
    private PreparedStatement delete;
    private PreparedStatement selectAll;
    private PreparedStatement selectByEdicao;
    private PreparedStatement selectByCidade;
    private PreparedStatement selectByTipo;

    public ArtigoRepository() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionSingleton.getConnection();
        insert = connection.prepareStatement("INSERT INTO public.artigos VALUES(?, ?, ?, ?)");
        update = connection.prepareStatement("UPDATE public.artigos SET titulo=?, tipoid=?, edicaoid=? WHERE artigoid=?");
        newId = connection.prepareStatement("SELECT max(artigoid)+1 FROM public.artigos");
        selectAll = connection.prepareStatement("SELECT * FROM public.artigos");
        select = connection.prepareStatement("SELECT * FROM public.artigos WHERE artigoid=?");
        delete = connection.prepareStatement("DELETE FROM artigos WHERE artigoid=?");
        selectByEdicao = connection.prepareStatement("SELECT * FROM public.artigos WHERE edicaoid=?");
        selectByCidade = connection.prepareStatement("SELECT a.* FROM public.artigos a JOIN public.edicoes e ON a.edicaoid = e.edicaoid WHERE e.cidade=?");
        selectByTipo = connection.prepareStatement("SELECT * FROM artigos WHERE tipoid IN (SELECT tipoid FROM tipos WHERE tipoid=?)");
    }

    public static ArtigoRepository getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new ArtigoRepository();
        }
        return instance;
    }

    public Optional<Artigo> findById(Integer artigoid) throws SQLException {
        select.setInt(1, artigoid);
        ResultSet rs = select.executeQuery();
        if (rs.next())
            return Optional.of(new Artigo(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getInt(4)
            ));
        return Optional.empty();
    }

    public void update(Artigo artigo) throws SQLException {
        update.setString(1, artigo.getTitulo());
        update.setInt(2, artigo.getTipoid());
        update.setInt(3, artigo.getEdicaoid());
        update.setInt(4, artigo.getArtigoid());
        update.executeUpdate();
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
        return getArtigos(selectAll);
    }

    public void delete(Artigo artigo) throws SQLException {
        delete.setInt(1, artigo.getArtigoid());
        delete.executeUpdate();
    }

    public List<Artigo> findByEdicao(Edicao edicao) throws SQLException {
        selectByEdicao.setInt(1, edicao.getEdicaoid());
        return getArtigos(selectByEdicao);
    }

    public List<Artigo> findByCidadeEdicao(Edicao edicao) throws SQLException {
        selectByCidade.setString(1, edicao.getCidade());
        return getArtigos(selectByCidade);
    }

    private List<Artigo> findByTipo(Tipo tipo) throws SQLException {
        selectByTipo.setInt(1, tipo.getTipoid());
        return getArtigos(selectByTipo);
    }


    private List<Artigo> getArtigos(PreparedStatement selectByCidade) throws SQLException {
        ResultSet rs = selectByCidade.executeQuery();
        List<Artigo> list = new ArrayList<>();
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
