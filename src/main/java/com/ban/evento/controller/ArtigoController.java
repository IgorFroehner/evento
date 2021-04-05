package com.ban.evento.controller;

import com.ban.evento.model.Artigo;
import com.ban.evento.model.Edicao;
import com.ban.evento.service.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/artigo")
public class ArtigoController {

    @Autowired
    private ArtigoService service;

    @GetMapping
    public ResponseEntity findAll(){
        try {
            List<Artigo> res = service.findAll();
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
            return new ResponseEntity("Erro Interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("by_edicao/{id}")
    public ResponseEntity findByEdicao(Integer edicaoid) {
        try {
            Edicao edicao = new Edicao();
            edicao.setEdicaoid(edicaoid);
            return new ResponseEntity(this.service.findByEdicao(edicao), HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity("Erro Interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("by_cidade/{cidade}")
    public ResponseEntity findByCidade(String cidade) {
        try {
            Edicao edicao = new Edicao();
            edicao.setCidade(cidade);
            return new ResponseEntity(this.service.findByCidade(edicao), HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity("Erro Interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("by_nome_autor/{nome_autor}")
    public ResponseEntity findByNomeAutor(String nomeAutor) {
        try {
            return new ResponseEntity(this.service.findByNomeAutor(nomeAutor), HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity("Erro Interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
