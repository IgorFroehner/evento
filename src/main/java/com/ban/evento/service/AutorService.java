package com.ban.evento.service;

import com.ban.evento.model.Autor;
import com.ban.evento.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public AutorService() throws SQLException, ClassNotFoundException {
        this.repository = AutorRepository.getInstance();
    }

    public void save(Autor autor) throws SQLException {
        this.repository.save(autor);
    }

    public Optional<Autor> findById(Integer id) throws SQLException {
        return this.repository.findById(id);
    }

    public List<Autor> findAll() throws SQLException {
        return this.repository.findAll();
    }

}
