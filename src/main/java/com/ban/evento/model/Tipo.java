package com.ban.evento.model;

import java.util.Objects;

public class Tipo {

    private Integer tipoid;
    private String nome;

    public Tipo(Integer tipoid, String nome) {
        this.tipoid = tipoid;
        this.nome = nome;
    }

    public Tipo() {
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

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tipo)) return false;
        Tipo tipo = (Tipo) o;
        return Objects.equals(tipoid, tipo.tipoid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoid, nome);
    }

}
