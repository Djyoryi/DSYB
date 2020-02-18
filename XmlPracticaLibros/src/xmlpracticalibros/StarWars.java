/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlpracticalibros;

/**
 *
 * @author fecq8
 */
public class StarWars {
    private String cod_personaje;
    private String nombre;
    private String lado;
    private String raza;
    private String pri_aparicion;
    private String ult_aparicion;

    public StarWars() {
    }

    public String getCod_personaje() {
        return cod_personaje;
    }

    public void setCod_personaje(String cod_personaje) {
        this.cod_personaje = cod_personaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getPri_aparicion() {
        return pri_aparicion;
    }

    public void setPri_aparicion(String pri_aparicion) {
        this.pri_aparicion = pri_aparicion;
    }

    public String getUlt_aparicion() {
        return ult_aparicion;
    }

    public void setUlt_aparicion(String ult_aparicion) {
        this.ult_aparicion = ult_aparicion;
    }

    @Override
    public String toString() {
        return "StarWars{" + "cod_personaje=" + cod_personaje + ", nombre=" + nombre + ", lado=" + lado + ", raza=" + raza + ", pri_aparicion=" + pri_aparicion + ", ult_aparicion=" + ult_aparicion + '}';
    }
    
    
}
