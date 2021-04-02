package com.ban.evento.service;

import com.ban.evento.model.Artigo;
import com.ban.evento.repository.ArtigoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ArtigoService {

    private ArtigoRepository repository;

    public ArtigoService() throws SQLException, ClassNotFoundException {
        this.repository = ArtigoRepository.getInstance();
    }

    public void save(Artigo artigo) throws SQLException {
        this.repository.save(artigo);
    }

    public List<Artigo> findAll() throws SQLException {
        return this.repository.findAll();
    }

}
