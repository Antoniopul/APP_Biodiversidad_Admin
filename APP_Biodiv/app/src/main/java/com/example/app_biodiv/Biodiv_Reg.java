package com.example.app_biodiv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Biodiv_Reg extends AppCompatActivity {
    Button btnreganimales;
    TextView categ,nomb,desc,didtgeo,stat,hist,aut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodiv_reg);
        btnreganimales=(Button) findViewById(R.id.btnregAnimal);
        categ=(TextView)findViewById(R.id.txtcategoriabio2);
        nomb=(TextView)findViewById(R.id.txtnombrebio2);
        desc=(TextView)findViewById(R.id.txtdescripbio2);
        didtgeo=(TextView)findViewById(R.id.txtdesgeobio2);
        stat=(TextView)findViewById(R.id.txtstatusbio2);
        hist=(TextView)findViewById(R.id.txthistoriabio2);
        aut=(TextView)findViewById(R.id.txtautoresbio2);

        btnreganimales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });
    }

    private void registrar(){
        final String cat=categ.getText().toString().trim();
        final String nom=nomb.getText().toString().trim();
        final String des=desc.getText().toString().trim();
        final String disgeo=didtgeo.getText().toString().trim();
        final String estad=stat.getText().toString().trim();
        final String histo=hist.getText().toString().trim();
        final String autor=aut.getText().toString().trim();

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registrando...");
        progressDialog.show();

        if (cat.isEmpty()){
            Toast.makeText(this, "Ingrese Categoria",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(nom.isEmpty()){
            Toast.makeText(this, "Ingrese Nombre",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (des.isEmpty()){
            Toast.makeText(this, "Ingrese la descripcion",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (disgeo.isEmpty()){
            Toast.makeText(this, "Ingrese la disribución geografica",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (estad.isEmpty()){
            Toast.makeText(this, "Ingrese el estado de la especie",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (histo.isEmpty()){
            Toast.makeText(this, "Ingrese el Parque",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (autor.isEmpty()){
            Toast.makeText(this, "Ingrese el autor",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            progressDialog.show();
            StringRequest request=new StringRequest(Request.Method.POST, "https://biodivparques.000webhostapp.com/reg_biodiv.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("datos insertados")) {
                        Toast.makeText(Biodiv_Reg.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        startActivity(new Intent(getApplicationContext(), Biodiv.class));
                        finish();
                    } else {
                        Toast.makeText(Biodiv_Reg.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Biodiv_Reg.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params=new HashMap<>();
                    params.put("categoria",cat);
                    params.put("titulo",nom);
                    params.put("descri",des);
                    params.put("dis_geo",disgeo);
                    params.put("estatus",estad);
                    params.put("historia",histo);
                    params.put("autores",autor);

                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(Biodiv_Reg.this);
            requestQueue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}