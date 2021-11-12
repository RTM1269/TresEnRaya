package com.rtm.tresenraya;

public class jugador {
    private String nombre;
    private char ficha;
    public jugador(String nom, char fich){
        this.nombre = nom;
        this.ficha = fich;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getFicha() {
        return ficha;
    }

}
