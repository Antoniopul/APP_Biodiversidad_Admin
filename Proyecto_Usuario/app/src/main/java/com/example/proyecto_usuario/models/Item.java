package com.example.proyecto_usuario.models;

public class Item {

    private int type;
    private Object object;
    private String id;

    public Item(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }
}
