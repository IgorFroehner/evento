package com.ban.evento.service;

import com.ban.evento.model.Autor;
import com.ban.evento.model.Tipo;
import com.ban.evento.repository.AutorRepository;
import com.ban.evento.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TipoService {

    @Autowired
    private TipoRepository repository;

    public TipoService() throws SQLException, ClassNotFoundException {
        this.repository = TipoRepository.getInstance();
    }

    public void save(Tipo tipo) throws SQLException {
        this.repository.save(tipo);
    }

    public Optional<Tipo> findById(Integer id) throws SQLException {
        return this.repository.findById(id);
    }

    public List<Tipo> findAll() throws SQLException {
        return this.repository.findAll();
    }

    public Integer nroArtigosFromTipoId(Integer tipoId) throws SQLException {
        return this.repository.nroArtigosFromTipo(tipoId);
    }

}
