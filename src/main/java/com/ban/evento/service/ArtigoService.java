package com.ban.evento.service;

import com.ban.evento.model.Artigo;
import com.ban.evento.model.Edicao;
import com.ban.evento.repository.ArtigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository repository;

    public ArtigoService() throws SQLException, ClassNotFoundException {
        this.repository = ArtigoRepository.getInstance();
    }

    public Optional<Artigo> findById(Integer id) throws SQLException {
        return this.repository.findById(id);
    }

    public void save(Artigo artigo) throws SQLException {
        this.repository.save(artigo);
    }

    public List<Artigo> findAll() throws SQLException {
        return this.repository.findAll();
    }

    public List<Artigo> findByEdicao(Edicao edicao) throws SQLException {
        return this.repository.findByEdicao(edicao);
    }

    public List<Artigo> findByCidade(Edicao edicao) throws SQLException {
        return this.repository.findByCidadeEdicao(edicao);
    }

    public List<Artigo> findByNomeAutor(String nomeAutor) throws SQLException {
        return this.repository.findByNomeAutor(nomeAutor);
    }

    public Integer newId() throws SQLException {
        return this.repository.selectNewId();
    }

}
