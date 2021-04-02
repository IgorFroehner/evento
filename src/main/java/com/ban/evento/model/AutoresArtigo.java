package com.ban.evento.model;

import java.util.Objects;

public class AutoresArtigo {

    private Integer id;

    private Integer autorid;

    private Integer artigoid;

    public AutoresArtigo() {
    }

    public AutoresArtigo(Integer id, Integer autorid, Integer artigoid) {
        this.id = id;
        this.autorid = autorid;
        this.artigoid = artigoid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAutorid() {
        return autorid;
    }

    public void setAutorid(Integer autorid) {
        this.autorid = autorid;
    }

    public Integer getArtigoid() {
        return artigoid;
    }

    public void setArtigoid(Integer artigoid) {
        this.artigoid = artigoid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AutoresArtigo)) return false;
        AutoresArtigo that = (AutoresArtigo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, autorid, artigoid);
    }

}
