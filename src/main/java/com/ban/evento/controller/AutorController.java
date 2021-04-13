package com.ban.evento.controller;

import com.ban.evento.model.Artigo;
import com.ban.evento.model.Autor;
import com.ban.evento.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class AutorController {

    @Autowired
    private AutorService service;

    @GetMapping("/autor")
    public String artigo(Model model) {
        model.addAttribute("titulo", "Autor");
        try {
            model.addAttribute("list_autor", service.findAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Houve um erro";
        }
        return "autor";
    }


    @GetMapping("/api/autor")
    public ResponseEntity findAll(){
        try {
            List<Autor> res = service.findAll();
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity(throwables.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/autor/{id}")
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
