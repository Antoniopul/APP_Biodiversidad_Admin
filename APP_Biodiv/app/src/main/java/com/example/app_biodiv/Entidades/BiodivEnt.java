package com.example.app_biodiv.Entidades;

public class BiodivEnt {

    private  String Id, Categoria, Titulo, Descrip, Dis_geo, Estat, Hist, Autores ;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescrip() {
        return Descrip;
    }

    public void setDescrip(String descrip) {
        Descrip = descrip;
    }

    public String getDis_geo() {
        return Dis_geo;
    }

    public void setDis_geo(String dis_geo) {
        Dis_geo = dis_geo;
    }

    public String getEstat() {
        return Estat;
    }

    public void setEstat(String estat) {
        Estat = estat;
    }

    public String getHist() {
        return Hist;
    }

    public void setHist(String hist) {
        Hist = hist;
    }

    public String getAutores() {
        return Autores;
    }

    public void setAutores(String autores) {
        Autores = autores;
    }

    public BiodivEnt(String id, String categoria, String titulo, String descrip, String dis_geo, String estat, String hist, String autores) {
        Id = id;
        Categoria = categoria;
        Titulo = titulo;
        Descrip = descrip;
        Dis_geo = dis_geo;
        Estat = estat;
        Hist = hist;
        Autores = autores;


    }
}
