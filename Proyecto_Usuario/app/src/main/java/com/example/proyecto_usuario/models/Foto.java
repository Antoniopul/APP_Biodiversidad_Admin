package com.example.proyecto_usuario.models;

public class Foto {
    public int fotoImage;
    public String fotoTitle, foto;

    public Foto(int fotoImage, String fotoTitle){
        this.fotoImage = fotoImage;
        this.fotoTitle = fotoTitle;
    }

    public int getFotoImage() {
        return fotoImage;
    }

    public String getFotoTitle() {
        return fotoTitle;
    }

}
