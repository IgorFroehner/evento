package com.ban.evento.DTOs;

public class AutorDTO {

    private Integer autorid;
    private String nome;
    private Character genero;

    public AutorDTO(Integer autorid, String nome, Character genero) {
        this.autorid = autorid;
        this.nome = nome;
        this.genero = genero;
    }

    public AutorDTO() {
    }

    public AutorDTO(String nome, Character genero) {
        this.nome = nome;
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "AutorDTO{" +
                "autorid=" + autorid +
                ", nome='" + nome + '\'' +
                ", genero=" + genero +
                '}';
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
}
