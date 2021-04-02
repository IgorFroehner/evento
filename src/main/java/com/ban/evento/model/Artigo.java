package com.ban.evento.model;

import java.util.Objects;

public class Artigo {

    private Integer artigoid;

    private String titulo;

    private Integer tipoid;

    private Integer edicaoid;

    public Artigo() {
    }

    public Artigo(Integer artigoid, String titulo, Integer tipoid, Integer edicaoid) {
        this.artigoid = artigoid;
        this.titulo = titulo;
        this.tipoid = tipoid;
        this.edicaoid = edicaoid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artigo)) return false;
        Artigo artigo = (Artigo) o;
        return Objects.equals(artigoid, artigo.artigoid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artigoid, titulo, tipoid, edicaoid);
    }

}
