package com.ban.evento.DTOs;

public class TipoDTO {

    private Integer tipoid;
    private String nome;
    private Integer nroArtigos;

    public TipoDTO(Integer tipoid, String nome) {
        this.tipoid = tipoid;
        this.nome = nome;
    }

    public TipoDTO(String nome) {
        this.nome = nome;
    }

    public Integer getNroArtigos() {
        return nroArtigos;
    }

    public void setNroArtigos(Integer nroArtigos) {
        this.nroArtigos = nroArtigos;
    }

    public TipoDTO() {
    }

    @Override
    public String toString() {
        return "TipoDTO{" +
                "tipoid=" + tipoid +
                ", nome='" + nome + '\'' +
                '}';
    }

    public Integer getTipoid() {
        return tipoid;
    }

    public void setTipoid(Integer tipoid) {
        this.tipoid = tipoid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
