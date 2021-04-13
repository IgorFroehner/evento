package com.ban.evento.repository;

import com.ban.evento.config.ConnectionSingleton;
import com.ban.evento.model.Artigo;
import com.ban.evento.model.Autor;
import com.ban.evento.model.Edicao;
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
public class ArtigoRepository {

    private static ArtigoRepository instance = null;
    private EdicaoRepository edicaoRepository;
    private TipoRepository tipoRepository;
    private AutorRepository autorRepository;
    private AutoresArtigoRepository autoresArtigoRepository;
    private PreparedStatement insert;
    private PreparedStatement update;
    private PreparedStatement select;
    private PreparedStatement newId;
    private PreparedStatement delete;
    private PreparedStatement selectAll;
    private PreparedStatement selectByEdicao;
    private PreparedStatement selectByCidade;
    private PreparedStatement selectByTipo;
    private PreparedStatement selectByNomeAutor;

    public ArtigoRepository() throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionSingleton.getConnection();
        insert = connection.prepareStatement("INSERT INTO public.artigos VALUES(?, ?, ?, ?)");
        update = connection.prepareStatement("UPDATE public.artigos SET titulo=?, tipoid=?, edicaoid=? WHERE artigoid=?");
        newId = connection.prepareStatement("SELECT max(artigoid)+1 FROM public.artigos");
        selectAll = connection.prepareStatement("SELECT * FROM public.artigos ORDER BY artigoid LIMIT 20");
        select = connection.prepareStatement("SELECT * FROM public.artigos WHERE artigoid=?");
        delete = connection.prepareStatement("DELETE FROM artigos WHERE artigoid=?");
        selectByEdicao = connection.prepareStatement("SELECT * FROM public.artigos WHERE edicaoid=?");
        selectByCidade = connection.prepareStatement("SELECT a.* FROM public.artigos a JOIN public.edicoes e ON a.edicaoid = e.edicaoid WHERE e.cidade=?");
        selectByTipo = connection.prepareStatement("SELECT * FROM artigos WHERE tipoid=?");
        selectByNomeAutor = connection.prepareStatement("SELECT * FROM artigos a JOIN autoresartigo aa ON a.artigoid = aa.artigoid WHERE aa.autorid IN (SELECT autorid FROM autores WHERE nome LIKE ?)");
        edicaoRepository = EdicaoRepository.getInstance();
        tipoRepository = TipoRepository.getInstance();
        autoresArtigoRepository = AutoresArtigoRepository.getInstance();
        autorRepository = AutorRepository.getInstance();
    }

    public static ArtigoRepository getInstance() throws SQLException, ClassNotFoundException {
        if (instance==null) {
            instance = new ArtigoRepository();
        }
        return instance;
    }

    private Edicao findEdicao(Integer edicaoid) throws SQLException {
        return edicaoRepository.findById(edicaoid).orElse(null);
    }

    private Tipo findTipo(Integer tipoid) throws SQLException {
        return tipoRepository.findById(tipoid).orElse(null);
    }

    private List<Autor> findAutores(Integer idartigo) throws SQLException {
        return autorRepository.findByArtigoId(idartigo);
    }

    public Optional<Artigo> findById(Integer artigoid) throws SQLException {
        select.setInt(1, artigoid);
        ResultSet rs = select.executeQuery();
        if (rs.next()){
            Integer id = rs.getInt(1);
            return Optional.of(new Artigo(
                    id,
                    rs.getString(2),
                    findTipo(rs.getInt(3)),
                    findEdicao(rs.getInt(4)),
                    findAutores(id)
            ));
        }
        return Optional.empty();
    }

    public void update(Artigo artigo) throws SQLException {
        update.setString(1, artigo.getTitulo());
        update.setInt(2, artigo.getTipo().getTipoid());
        update.setInt(3, artigo.getEdicao().getEdicaoid());
        update.setInt(4, artigo.getArtigoid());
        update.executeUpdate();
    }

    public void save(Artigo artigo) throws SQLException {
        Integer newId = selectNewId();
        this.insert.setInt(1, newId);
        this.insert.setString(2, artigo.getTitulo());
        this.insert.setInt(3, artigo.getTipo().getTipoid());
        this.insert.setInt(4, artigo.getEdicao().getEdicaoid());
        this.insert.executeUpdate();
        for (Autor autor : artigo.getAutores()){
            autoresArtigoRepository.save(autor.getAutorid(), newId);
        }
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

    public List<Artigo> findByTipo(Tipo tipo) throws SQLException {
        selectByTipo.setInt(1, tipo.getTipoid());
        return getArtigos(selectByTipo);
    }

    public List<Artigo> findByNomeAutor(String nomeAutor) throws SQLException {
        selectByNomeAutor.setString(1, nomeAutor+"%");
        return getArtigos(selectByNomeAutor);
    }


    private List<Artigo> getArtigos(PreparedStatement selectByCidade) throws SQLException {
        ResultSet rs = selectByCidade.executeQuery();
        List<Artigo> list = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt(1);
            list.add(new Artigo(
                    id,
                    rs.getString(2),
                    findTipo(rs.getInt(3)),
                    findEdicao(rs.getInt(4)),
                    findAutores(id)
            ));
        }
        return list;
    }


}
