package com.ban.evento.controller;

import com.ban.evento.model.Artigo;
import com.ban.evento.model.Autor;
import com.ban.evento.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService service;

    @GetMapping
    public ResponseEntity findAll(){
        try {
            List<Autor> res = service.findAll();
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity(throwables.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
