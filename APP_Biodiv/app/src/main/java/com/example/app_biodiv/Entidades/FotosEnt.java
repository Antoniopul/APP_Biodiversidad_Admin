package com.example.app_biodiv.Entidades;

import android.graphics.Bitmap;

public class FotosEnt {

    private String IDfoto, IDasociada,Categoria, Imagen, Nombre, Autores;

    public FotosEnt(String IDfoto, String IDasociada, String categoria, String imagen, String nombre, String autores) {
        this.IDfoto = IDfoto;
        this.IDasociada = IDasociada;
        Categoria = categoria;
        Imagen = imagen;
        Nombre = nombre;
        Autores = autores;
    }

    public String getIDfoto() {
        return IDfoto;
    }

    public void setIDfoto(String IDfoto) {
        this.IDfoto = IDfoto;
    }

    public String getIDasociada() {
        return IDasociada;
    }

    public void setIDasociada(String IDasociada) {
        this.IDasociada = IDasociada;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getAutores() {
        return Autores;
    }

    public void setAutores(String autores) {
        Autores = autores;
    }
}
