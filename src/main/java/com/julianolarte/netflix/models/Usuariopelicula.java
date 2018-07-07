package com.julianolarte.netflix.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Usuariopelicula {
    private int id;
    private Date fecha;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuariopelicula that = (Usuariopelicula) o;
        return id == that.id &&
                Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fecha);
    }
}
