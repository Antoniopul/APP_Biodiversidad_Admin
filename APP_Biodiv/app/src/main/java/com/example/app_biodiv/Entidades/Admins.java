package com.example.app_biodiv.Entidades;

public class Admins {

    private  String Id,Usuario, Pass;

    public Admins(String id,String usuario, String pass) {
        Id = id;
        Usuario = usuario;
        Pass = pass;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

}
