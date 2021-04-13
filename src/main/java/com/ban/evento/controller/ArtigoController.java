package com.ban.evento.controller;

import com.ban.evento.DTOs.ArtigoDTO;
import com.ban.evento.model.Artigo;
import com.ban.evento.model.Autor;
import com.ban.evento.model.Edicao;
import com.ban.evento.service.ArtigoService;
import com.ban.evento.service.AutorService;
import com.ban.evento.service.EdicaoService;
import com.ban.evento.service.TipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArtigoController {

    @Autowired
    private ArtigoService service;

    @Autowired
    private TipoService tipoService;

    @Autowired
    private EdicaoService edicaoService;

    @Autowired
    private AutorService autorService;

    @GetMapping("/artigo")
    public String artigo(Model model) {
        model.addAttribute("titulo", "Artigos");
        try {
            model.addAttribute("list_artigos", service.findAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Houve um erro";
        }
        return "artigo";
    }

    @GetMapping("/artigo_form")
    public String artigo_form(Model model) {
        try {
            model.addAttribute("tipos", tipoService.findAll());
            model.addAttribute("edicoes", edicaoService.findAll());
            model.addAttribute("newArtigo", new ArtigoDTO());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Houve um erro";
        }
        return "/forms/artigo_form";
    }

    @PostMapping("/save_artigo")
    public String save_artigo(@ModelAttribute ArtigoDTO artigoDTO) {
        try {
            List<Autor> autores = new ArrayList<>();
            for (String autorid : artigoDTO.getAutoresids().split(" ")){
                autores.add(autorService.findById(Integer.parseInt(autorid)).orElse(null));
            }
            Artigo newArtigo = new Artigo(
                    artigoDTO.getArtigoid(),
                    artigoDTO.getTitulo(),
                    tipoService.findById(artigoDTO.getTipoid()).orElse(null),
                    edicaoService.findById(artigoDTO.getEdicaoid()).orElse(null),
                    autores);
            service.save(newArtigo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/artigo";
    }

    @PostMapping("/api/artigo")
    public ResponseEntity save(@RequestBody Artigo artigo) {
        try {
            service.save(artigo);
            return new ResponseEntity("Artigo cadastrado", HttpStatus.ACCEPTED);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity(throwables.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/artigo")
    public ResponseEntity findAll(){
        try {
            List<Artigo> res = service.findAll();
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity(throwables.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/artigo/{id}")
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

    @GetMapping("/api/artigo/by_edicao/{id}")
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

    @GetMapping("/api/artigo/by_cidade/{cidade}")
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

    @GetMapping("/api/artigo/by_nome_autor/{nome_autor}")
    public ResponseEntity findByNomeAutor(String nomeAutor) {
        try {
            return new ResponseEntity(this.service.findByNomeAutor(nomeAutor), HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity("Erro Interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
