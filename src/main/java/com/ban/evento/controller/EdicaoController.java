package com.ban.evento.controller;

import com.ban.evento.DTOs.ArtigoDTO;
import com.ban.evento.DTOs.EdicaoDTO;
import com.ban.evento.model.Artigo;
import com.ban.evento.model.Autor;
import com.ban.evento.model.Edicao;
import com.ban.evento.repository.EdicaoRepository;
import com.ban.evento.service.EdicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EdicaoController {

    @Autowired
    private EdicaoService service;

    @GetMapping("/edicao")
    public String artigo(Model model) {
        model.addAttribute("titulo", "Edições");
        try {
            model.addAttribute("list_edicao", service.findAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Houve um erro";
        }
        return "edicao";
    }

    @GetMapping("/edicao_form")
    public String artigo_form(Model model) {
        model.addAttribute("newEdicao", new EdicaoDTO());
        return "/forms/edicao_form";
    }

    @PostMapping("/save_edicao")
    public String save_artigo(@ModelAttribute EdicaoDTO edicaoDTO) {
        try {
            Edicao edicao = new Edicao(
                    null,
                    edicaoDTO.getCidade(),
                    edicaoDTO.getUf(),
                    edicaoDTO.getQtdparticipantes(),
                    edicaoDTO.getAno());
            service.save(edicao);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/edicao";
    }

    @GetMapping("/api/edicao")
    public ResponseEntity findAll(){
        try {
            List<Edicao> res = service.findAll();
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity(throwables.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/edicao/{id}")
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
