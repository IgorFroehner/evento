package com.ban.evento.controller;

import com.ban.evento.DTOs.EdicaoDTO;
import com.ban.evento.DTOs.TipoDTO;
import com.ban.evento.model.Edicao;
import com.ban.evento.model.Tipo;
import com.ban.evento.service.TipoService;
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
import java.util.List;

@Controller
public class TipoController {

    @Autowired
    private TipoService service;

    @GetMapping("/tipo")
    public String artigo(Model model) {
        model.addAttribute("titulo", "Tipo");
        try {
            model.addAttribute("list_tipos", service.findAll());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Houve um erro";
        }
        return "tipo";
    }

    @GetMapping("/tipo_form")
    public String artigo_form(Model model) {
        model.addAttribute("newTipo", new TipoDTO());
        return "/forms/tipo_form";
    }

    @PostMapping("/save_tipo")
    public String save_artigo(@ModelAttribute TipoDTO tipoDTO) {
        try {
            Tipo tipo = new Tipo(null, tipoDTO.getNome());
            service.save(tipo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/tipo";
    }

    @GetMapping("api/tipo")
    public ResponseEntity findAll(){
        try {
            List<Tipo> res = service.findAll();
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new ResponseEntity(throwables.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/tipo/{id}")
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
