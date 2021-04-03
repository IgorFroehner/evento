package com.ban.evento.controller;

import com.ban.evento.model.Artigo;
import com.ban.evento.model.Edicao;
import com.ban.evento.repository.EdicaoRepository;
import com.ban.evento.service.EdicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/edicao")
public class EdicaoController {

    @Autowired
    private EdicaoService service;

    @GetMapping
    public ResponseEntity findAll(){
        try {
            List<Edicao> res = service.findAll();
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity(throwables.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(Integer id) {
        try {
            return this.service.findById(id).map(
                    artigo -> new ResponseEntity(artigo, HttpStatus.OK)
            ).orElseGet(
                    () -> new ResponseEntity("Id não encontrado", HttpStatus.BAD_REQUEST));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity("Id não encontrado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
