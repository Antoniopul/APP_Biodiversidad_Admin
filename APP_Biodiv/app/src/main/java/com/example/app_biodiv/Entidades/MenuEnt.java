package com.example.app_biodiv.Entidades;

public class MenuEnt {
    private int Imgaen;
    private String Titutlo, Descripcion;

    public int getImgaen() {
        return Imgaen;
    }

    public void setImgaen(int imgaen) {
        Imgaen = imgaen;
    }

    public String getTitutlo() {
        return Titutlo;
    }

    public void setTitutlo(String titutlo) {
        Titutlo = titutlo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public MenuEnt(int imgaen, String titutlo, String descripcion) {
        Imgaen = imgaen;
        Titutlo = titutlo;
        Descripcion = descripcion;


    }
}
