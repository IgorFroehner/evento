package com.ban.evento.model.artigo;

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
}
