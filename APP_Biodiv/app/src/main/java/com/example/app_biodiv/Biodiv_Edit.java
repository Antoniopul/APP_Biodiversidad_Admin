package com.example.app_biodiv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class Biodiv_Edit extends AppCompatActivity {
    FloatingActionButton btneditbiodiv;
    TextView categ,nomb,desc,didtgeo,stat,hist,aut;

    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodiv_edit);
        btneditbiodiv=(FloatingActionButton) findViewById(R.id.btneditBiodiv);
        categ=(TextView)findViewById(R.id.txtcategoriabio);
        nomb=(TextView)findViewById(R.id.txtnombrebio);
        desc=(TextView)findViewById(R.id.txtdescripbio);
        didtgeo=(TextView)findViewById(R.id.txtdesgeobio);
        stat=(TextView)findViewById(R.id.txtstatusbio);
        hist=(TextView)findViewById(R.id.txthistoriabio);
        aut=(TextView)findViewById(R.id.txtautoresbio);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        categ.setText(Biodiv.itemsArrayList.get(position).getCategoria());
        nomb.setText(Biodiv.itemsArrayList.get(position).getTitulo());
        desc.setText(Biodiv.itemsArrayList.get(position).getDescrip());
        didtgeo.setText(Biodiv.itemsArrayList.get(position).getDis_geo());
        stat.setText(Biodiv.itemsArrayList.get(position).getEstat());
        hist.setText(Biodiv.itemsArrayList.get(position).getHist());
        aut.setText(Biodiv.itemsArrayList.get(position).getAutores());

        btneditbiodiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(Biodiv_Edit.this);
                alertDialog.setMessage("¿Desea actualizar los datos?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                actualizar();
                                finish();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo=alertDialog.create();
                titulo.setTitle("Actualizar Datos");
                titulo.show();
            }
        });

    }

    public void actualizar(){
        String id = Biodiv.itemsArrayList.get(position).getId();
        String URL="https://biodivparques.000webhostapp.com/edit_biodiv.php";
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("succes")){
                    Toast.makeText(Biodiv_Edit.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Biodiv.class));
                    finish();
                }else{
                    Toast.makeText(Biodiv_Edit.this, "Error al Actalizar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Biodiv_Edit.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("id", id);
                params.put("categoria", categ.getText().toString());
                params.put("titulo", nomb.getText().toString());
                params.put("descri", desc.getText().toString());
                params.put("dis_geo", didtgeo.getText().toString());
                params.put("estatus", stat.getText().toString());
                params.put("historia", hist.getText().toString());
                params.put("autores", aut.getText().toString());

                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(Biodiv_Edit.this);
        requestQueue.add(request);
    }

//    private void dell(final String titulo){
//        String URL="https://biodivparques.000webhostapp.com/del_biodiv.php";
//        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equalsIgnoreCase("succes")) {
//                    Toast.makeText(Biodiv_Edit.this, "Eliminado", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), Biodiversidad.class));
//                } else {
//                    Toast.makeText(Biodiv_Edit.this, "Error al Eliminar", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Biodiv_Edit.this, "Error al Eliminar", Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String>params=new HashMap<>();
//                params.put("titulo", titulo);
//                return params;
//            }
//        };
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        requestQueue.add(request);
//    }
}