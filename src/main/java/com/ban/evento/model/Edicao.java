package com.ban.evento.model;

import java.util.Objects;

public class Edicao {

    private Integer edicaoid;

    private String cidade;

    private String uf;

    private Integer qtdparticipantes;

    private Integer ano;

    public Edicao() {
    }

    public Edicao(Integer edicaoid, String cidade, String uf, Integer qtdparticipantes, Integer ano) {
        this.edicaoid = edicaoid;
        this.cidade = cidade;
        this.uf = uf;
        this.qtdparticipantes = qtdparticipantes;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return cidade + "-" + uf + " " + ano;
    }

    public Integer getEdicaoid() {
        return edicaoid;
    }

    public void setEdicaoid(Integer edicaoid) {
        this.edicaoid = edicaoid;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getQtdparticipantes() {
        return qtdparticipantes;
    }

    public void setQtdparticipantes(Integer qtdparticipantes) {
        this.qtdparticipantes = qtdparticipantes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edicao)) return false;
        Edicao edicao = (Edicao) o;
        return Objects.equals(edicaoid, edicao.edicaoid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(edicaoid, cidade, uf, qtdparticipantes, ano);
    }

}
