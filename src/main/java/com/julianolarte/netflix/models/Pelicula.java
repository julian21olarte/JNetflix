package com.julianolarte.netflix.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Pelicula {
    private int id;
    private String nombre;
    private String resumen;
    private String idioma;
    private String ano;
    private int duracionmin;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "resumen")
    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    @Basic
    @Column(name = "idioma")
    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Basic
    @Column(name = "ano")
    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Basic
    @Column(name = "duracionmin")
    public int getDuracionmin() {
        return duracionmin;
    }

    public void setDuracionmin(int duracionmin) {
        this.duracionmin = duracionmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return id == pelicula.id &&
                duracionmin == pelicula.duracionmin &&
                Objects.equals(nombre, pelicula.nombre) &&
                Objects.equals(resumen, pelicula.resumen) &&
                Objects.equals(idioma, pelicula.idioma) &&
                Objects.equals(ano, pelicula.ano);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nombre, resumen, idioma, ano, duracionmin);
    }
}
