package com.ban.evento.service;

import com.ban.evento.model.Artigo;
import com.ban.evento.model.Edicao;
import com.ban.evento.repository.ArtigoRepository;
import com.ban.evento.repository.EdicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class EdicaoService {

    @Autowired
    private EdicaoRepository repository;

    public EdicaoService() throws SQLException, ClassNotFoundException {
        this.repository = EdicaoRepository.getInstance();
    }

    public Optional<Edicao> findById(Integer id) throws SQLException {
        return this.repository.findById(id);
    }

    public void save(Edicao edicao) throws SQLException {
        this.repository.save(edicao);
    }

    public List<Edicao> findAll() throws SQLException {
        return this.repository.findAll();
    }

}
