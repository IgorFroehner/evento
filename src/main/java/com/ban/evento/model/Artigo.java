package com.ban.evento.model;

import java.util.List;
import java.util.Objects;

public class Artigo {

    private Integer artigoid;

    private String titulo;

    private Tipo tipo;

    private Edicao edicao;

    private List<Autor> autores;

    public Artigo() {
    }

    public Artigo(Integer artigoid, String titulo, Tipo tipo, Edicao edicao) {
        this.artigoid = artigoid;
        this.titulo = titulo;
        this.tipo = tipo;
        this.edicao = edicao;
    }

    public Artigo(Integer artigoid, String titulo, Tipo tipo, Edicao edicao, List<Autor> autores) {
        this.artigoid = artigoid;
        this.titulo = titulo;
        this.tipo = tipo;
        this.edicao = edicao;
        this.autores = autores;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Edicao getEdicao() {
        return edicao;
    }

    public void setEdicao(Edicao edicao) {
        this.edicao = edicao;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artigo)) return false;
        Artigo artigo = (Artigo) o;
        return Objects.equals(artigoid, artigo.artigoid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artigoid, titulo, tipo, edicao);
    }

    public List<Autor> getAutores() {
        return autores;
    }
}
