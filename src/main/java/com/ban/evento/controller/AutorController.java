package com.ban.evento.controller;

import com.ban.evento.DTOs.AutorDTO;
import com.ban.evento.DTOs.EdicaoDTO;
import com.ban.evento.model.Artigo;
import com.ban.evento.model.Autor;
import com.ban.evento.model.Edicao;
import com.ban.evento.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AutorController {

    @Autowired
    private AutorService service;

    @GetMapping("/autor")
    public String artigo(Model model) {
        model.addAttribute("titulo", "Autor");
        try {
            List<AutorDTO> list_autor = convertToDTO(service.findAll());
            for (AutorDTO autorDTO : list_autor) {
                autorDTO.setNroArtigosPublicados(service.nroArtigosFromAutorId(autorDTO.getAutorid()));
            }
            model.addAttribute("list_autor", list_autor);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Houve um erro";
        }
        return "autor";
    }

    @GetMapping("/autor_form")
    public String artigo_form(Model model) {
        model.addAttribute("newAutor", new AutorDTO());
        return "/forms/autor_form";
    }

    @PostMapping("/save_autor")
    public String save_artigo(@ModelAttribute AutorDTO autorDTO) {
        try {
            Autor autor = new Autor(
                    null,
                    autorDTO.getNome(),
                    autorDTO.getGenero()
            );
            service.save(autor);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/autor";
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

    private List<AutorDTO> convertToDTO(List<Autor> autores) {
        List<AutorDTO> res = new ArrayList<>();
        for (Autor autor : autores) {
            res.add(new AutorDTO(autor.getAutorid(), autor.getNome(), autor.getGenero()));
        }
        return res;
    }

}
