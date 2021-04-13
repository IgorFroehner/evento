package com.ban.evento.model;

import java.util.Objects;

public class Autor {

    private Integer autorid;

    private String nome;

    private Character genero;

    @Override
    public String toString() {
        return "Autor{" +
                "autorid=" + autorid +
                ", nome='" + nome + '\'' +
                ", genero=" + genero +
                '}';
    }

    public Autor() {
    }

    public Autor(Integer autorid, String nome, Character genero) {
        this.autorid = autorid;
        this.nome = nome;
        this.genero = genero;
    }

    public Integer getAutorid() {
        return autorid;
    }

    public void setAutorid(Integer autorid) {
        this.autorid = autorid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Character getGenero() {
        return genero;
    }

    public void setGenero(Character genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        Autor autor = (Autor) o;
        return Objects.equals(autorid, autor.autorid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autorid, nome, genero);
    }

}
