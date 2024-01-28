package com.example.app_biodiv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_biodiv.Adaptadores.AdapRecyFotos;
import com.example.app_biodiv.Entidades.FotosEnt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fotos_Bio_Rel extends AppCompatActivity {
    RecyclerView recy;
    FloatingActionButton btnreg, btndell;

    List<FotosEnt> fotosEntList;

    int id_dell=0;
    String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_bio_rel);
        recy = (RecyclerView) findViewById(R.id.recyclbio);
        btnreg = (FloatingActionButton) findViewById(R.id.regbtnbio);
        btndell=(FloatingActionButton)findViewById(R.id.btndelfotobio);

        Bundle obtener = getIntent().getExtras();
        String[] id = obtener.getStringArray("id");
        ObtenerDatos(id[0]);

        recy.setHasFixedSize(true);
        recy.setLayoutManager(new LinearLayoutManager(this));
        fotosEntList = new ArrayList<>();

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] datos = new String[]{id[0]};
                Bundle bundle = new Bundle();
                bundle.putStringArray("id", datos);
                Intent intent = new Intent(getApplicationContext(), Fotos_Bio_Reg.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        btndell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (id_dell>0) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Fotos_Bio_Rel.this);
                        alertDialog.setMessage("¿Desea eliminar la fotografia " + nombre + "?")
                                .setCancelable(false)
                                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dell(String.valueOf(id_dell));
                                        finish();
                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        id_dell = 0;
                                        dialogInterface.cancel();
                                    }
                                });
                        AlertDialog titulo = alertDialog.create();
                        titulo.setTitle("Eliminar Fotografia");
                        titulo.show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Seleccione una foto para eliminar", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }

    private void ObtenerDatos(String id_bio) {
        String URL = "https://biodivparques.000webhostapp.com/selec_foto_bio.php";
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if (succes.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject objectdatos = jsonArray.getJSONObject(i);

                            fotosEntList.add(new FotosEnt(
                                    objectdatos.getString("id"),
                                    objectdatos.getString("id_asos"),
                                    objectdatos.getString("cat"),
                                    objectdatos.getString("foto"),
                                    objectdatos.getString("nombre"),
                                    objectdatos.getString("autor")
                            ));
                        }
                        AdapRecyFotos adapRecyFotos = new AdapRecyFotos(Fotos_Bio_Rel.this, fotosEntList, new AdapRecyFotos.ItemClickListenerFoto() {
                            @Override
                            public void onItemClick(FotosEnt details) {
                                id_dell= Integer.parseInt(details.getIDfoto());
                                nombre=details.getNombre();
                                Toast.makeText(getApplicationContext(), "Seleccionada Foto "+ details.getNombre()+" para eliminar", Toast.LENGTH_SHORT).show();
                            }
                        });
                        recy.setAdapter(adapRecyFotos);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_biodiv", id_bio);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void dell(final String id_foto){
        String URL="https://biodivparques.000webhostapp.com/del_fotos.php";
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("succes")) {
                    Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Fotos_Bio.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Error al Eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error al Eliminar", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("idfoto", id_foto);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}