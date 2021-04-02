package com.ban.evento.service;

import com.ban.evento.model.artigo.Artigo;
import com.ban.evento.repository.ArtigoRepository;

import java.sql.SQLException;

public class ArtigoService {

    private ArtigoRepository repository;

    public ArtigoService() throws SQLException, ClassNotFoundException {
        this.repository = ArtigoRepository.getInstance();
    }

    public void save(Artigo artigo) {
        this.repository.save(artigo);
    }

}
