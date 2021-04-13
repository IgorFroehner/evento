package com.ban.evento.DTOs;

import com.ban.evento.model.Autor;
import com.ban.evento.model.Edicao;
import com.ban.evento.model.Tipo;

import java.util.List;

public class ArtigoDTO {

    private Integer artigoid;

    private String titulo;

    private Integer tipoid;

    private Integer edicaoid;

    private String autoresids;

    public ArtigoDTO(Integer artigoid, String titulo, Integer tipoid, Integer edicaoid, String autoresids) {
        this.artigoid = artigoid;
        this.titulo = titulo;
        this.tipoid = tipoid;
        this.edicaoid = edicaoid;
        this.autoresids = autoresids;
    }

    public ArtigoDTO() {
    }

    public ArtigoDTO(String titulo, Integer tipoid, Integer edicaoid, String autoresids) {
        this.titulo = titulo;
        this.tipoid = tipoid;
        this.edicaoid = edicaoid;
        this.autoresids = autoresids;
    }

    @Override
    public String toString() {
        return "ArtigoDTO{" +
                "artigoid=" + artigoid +
                ", titulo='" + titulo + '\'' +
                ", tipoid=" + tipoid +
                ", edicaoid=" + edicaoid +
                ", autoresids=" + autoresids +
                '}';
    }

    public Integer getArtigoid() {
        return artigoid;
    }

    public void setArtigoid(Integer artigoid) {
        this.artigoid = artigoid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTipoid() {
        return tipoid;
    }

    public void setTipoid(Integer tipoid) {
        this.tipoid = tipoid;
    }

    public Integer getEdicaoid() {
        return edicaoid;
    }

    public void setEdicaoid(Integer edicaoid) {
        this.edicaoid = edicaoid;
    }

    public String getAutoresids() {
        return autoresids;
    }

    public void setAutoresids(String autoresids) {
        this.autoresids = autoresids;
    }
}
