package com.example.proyecto_usuario.models;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String ruta = "https://soyitesi.club/accesoControlado/controller/AccesoAndroid.php";
    private Map<String, String> parametros;

    public LoginRequest(String usuario, String password, Response.Listener<String> listener){
        super(Request.Method.POST,ruta,listener,null);
        parametros = new HashMap<>();
        parametros.put("Usuario", usuario);
        parametros.put("Contrasena", password);
    }

    @Override
    protected Map<String,String> getParams(){
        return parametros;
    }
}
