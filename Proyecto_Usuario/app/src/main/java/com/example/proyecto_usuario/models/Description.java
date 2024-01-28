package com.example.proyecto_usuario.models;

public class Description {

    private String descTitle, desc, descCalle, desColin, desArea, desPerim, desLatit, desLong, desAreaR, desHist;

    public Description(String descTitle, String desc, String descCalle, String descColin, String descArea, String descPerim, String descLatit, String descLong, String descAreaR, String descHist) {
        this.descTitle = descTitle;
        this.desc = desc;
        this.descCalle = descCalle;
        this.desColin = descColin;
        this.desArea = descArea;
        this.desPerim = descPerim;
        this.desLatit = descLatit;
        this.desLong = descLong;
        this.desAreaR = descAreaR;
        this.desHist = descHist;
    }

    public String getDescTitle() {
        return descTitle;
    }

    public String getDesc() {
        return desc;
    }

    public String getDescCalle() {
        return descCalle;
    }

    public String getDesColin() {
        return desColin;
    }

    public String getDesArea() {
        return desArea;
    }

    public String getDesPerim() {
        return desPerim;
    }

    public String getDesLatit() {
        return desLatit;
    }

    public String getDesLong() {
        return desLong;
    }

    public String getDesAreaR() {
        return desAreaR;
    }

    public String getDesHist() {
        return desHist;
    }
}
