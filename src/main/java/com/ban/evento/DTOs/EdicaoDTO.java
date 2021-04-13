package com.ban.evento.DTOs;

public class EdicaoDTO {

    private Integer edicaoid;
    private String cidade;
    private String uf;
    private Integer qtdparticipantes;
    private Integer ano;

    public EdicaoDTO(Integer edicaoid, String cidade, String uf, Integer qtdparticipantes, Integer ano) {
        this.edicaoid = edicaoid;
        this.cidade = cidade;
        this.uf = uf;
        this.qtdparticipantes = qtdparticipantes;
        this.ano = ano;
    }

    public EdicaoDTO(String cidade, String uf, Integer qtdparticipantes, Integer ano) {
        this.cidade = cidade;
        this.uf = uf;
        this.qtdparticipantes = qtdparticipantes;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "EdicaoDTO{" +
                "edicaoid=" + edicaoid +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", qtdparticipantes=" + qtdparticipantes +
                ", ano=" + ano +
                '}';
    }

    public EdicaoDTO() {
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
}
