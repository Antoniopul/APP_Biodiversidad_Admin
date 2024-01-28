package com.example.app_biodiv.Entidades;

public class ParqueEnt {
    private String id,nombre,historia,area,perim,calle,
            col,muni,estado,latit,longit,colind,recreo;

    public ParqueEnt(String id, String nombre, String historia, String area, String perim,
                     String calle, String col, String muni, String estado,
                     String latit, String longit, String colind, String recreo) {
        this.id = id;
        this.nombre = nombre;
        this.historia = historia;
        this.area = area;
        this.perim = perim;
        this.calle = calle;
        this.col = col;
        this.muni = muni;
        this.estado = estado;
        this.latit = latit;
        this.longit = longit;
        this.colind = colind;
        this.recreo = recreo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPerim() {
        return perim;
    }

    public void setPerim(String perim) {
        this.perim = perim;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getMuni() {
        return muni;
    }

    public void setMuni(String muni) {
        this.muni = muni;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLatit() {
        return latit;
    }

    public void setLatit(String latit) {
        this.latit = latit;
    }

    public String getLongit() {
        return longit;
    }

    public void setLongit(String longit) {
        this.longit = longit;
    }

    public String getColind() {
        return colind;
    }

    public void setColind(String colind) {
        this.colind = colind;
    }

    public String getRecreo() {
        return recreo;
    }

    public void setRecreo(String recreo) {
        this.recreo = recreo;
    }
}
