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

public class Parques_Reg extends AppCompatActivity {
    Button btnregparque;
    TextView nombre,hist,area,perim,calle,
            col,muni,estado,latit,longit,colind,recreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parques_reg);
        btnregparque=(Button) findViewById(R.id.btnregParques);
        nombre=(TextView)findViewById(R.id.txtnombreparque);
        hist=(TextView)findViewById(R.id.txthistoriaparque);
        area=(TextView)findViewById(R.id.txtarea);
        perim=(TextView)findViewById(R.id.txtperim);
        calle=(TextView)findViewById(R.id.txtcalle);
        col=(TextView)findViewById(R.id.txtcolonia);
        muni=(TextView)findViewById(R.id.txtmunicipio);
        estado=(TextView)findViewById(R.id.txtestado);
        latit=(TextView)findViewById(R.id.txtlatitud);
        longit=(TextView)findViewById(R.id.txtlongitud);
        colind=(TextView)findViewById(R.id.txtcolindancias);
        recreo=(TextView)findViewById(R.id.txtrecreo);

        btnregparque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });
    }

    private void registrar(){
        final String titulo=nombre.getText().toString().trim();
        final String histo=hist.getText().toString().trim();
        final String ar=area.getText().toString().trim();
        final String per=perim.getText().toString().trim();
        final String call=calle.getText().toString().trim();
        final String coloni=col.getText().toString().trim();
        final String munis=muni.getText().toString().trim();
        final String est=estado.getText().toString().trim();
        final String lat=latit.getText().toString().trim();
        final String lon=longit.getText().toString().trim();
        final String colin=colind.getText().toString().trim();
        final String rec=recreo.getText().toString().trim();


        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registrando...");
        progressDialog.show();

        if (titulo.isEmpty()){
            Toast.makeText(this, "Ingrese el nombre del parque",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (histo.isEmpty()){
            Toast.makeText(this, "Ingrese su Historia",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (ar.isEmpty()){
            Toast.makeText(this, "Ingrese el area",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (per.isEmpty()){
            Toast.makeText(this, "Ingrese el perimetro",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (call.isEmpty()){
            Toast.makeText(this, "Ingrese la calle",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (coloni.isEmpty()){
            Toast.makeText(this, "Ingrese la colonia",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (munis.isEmpty()){
            Toast.makeText(this, "Ingrese el municipio",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (est.isEmpty()){
            Toast.makeText(this, "Ingrese el estado de la especie",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (lat.isEmpty()){
            Toast.makeText(this, "Ingrese la latitud",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (lon.isEmpty()){
            Toast.makeText(this, "Ingrese la longitud",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (colin.isEmpty()){
            Toast.makeText(this, "Ingrese las colindancias",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (rec.isEmpty()){
            Toast.makeText(this, "Ingrese la zona de recreo",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            progressDialog.show();
            StringRequest request=new StringRequest(Request.Method.POST, "https://biodivparques.000webhostapp.com/reg_parque.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("succes")) {
                        Toast.makeText(Parques_Reg.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        startActivity(new Intent(getApplicationContext(), Parque.class));
                        finish();
                    } else {
                        Toast.makeText(Parques_Reg.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Parques_Reg.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params=new HashMap<>();
                    params.put("titulo",titulo);
                    params.put("historia",histo);
                    params.put("area",ar);
                    params.put("perim",per);
                    params.put("calle",call);
                    params.put("col",coloni);
                    params.put("municip",munis);
                    params.put("estado",est);
                    params.put("latit", lat);
                    params.put("long",lon);
                    params.put("colind",colin);
                    params.put("recreo",rec);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(Parques_Reg.this);
            requestQueue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}