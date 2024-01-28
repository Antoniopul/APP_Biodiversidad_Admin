package com.example.app_biodiv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_biodiv.Adaptadores.AdapBiodiv;
import com.example.app_biodiv.Entidades.BiodivEnt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RelacionBiodiv extends AppCompatActivity {
    ListView listabio;

    AdapBiodiv adapBiodiv;


    public static ArrayList<BiodivEnt> itemsArrayList=new ArrayList<>();
    BiodivEnt items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacion_biodiv);
        listabio=(ListView) findViewById(R.id.listarelbio);

        Bundle obtener=getIntent().getExtras();
        String[] id_parq=obtener.getStringArray("id");

        adapBiodiv =new AdapBiodiv(this , itemsArrayList);
        listabio.setAdapter(adapBiodiv);

        listabio.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id) {
                int pos= Integer.parseInt(id_parq[1]);
                String idpospar=id_parq[0];
                String idposdiv=RelacionBiodiv.itemsArrayList.get(position).getId();
                registrar(pos,idpospar,idposdiv);

            }
        });

        ObtenerDatos(id_parq[0]);
    }

    private void  ObtenerDatos(String id_parq){
        String URL="https://biodivparques.000webhostapp.com/selec_bio_no_rel.php";
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                itemsArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if (succes.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject objectdatos = jsonArray.getJSONObject(i);

                            String id = objectdatos.getString("id");
                            String categoria = objectdatos.getString("categoria");
                            String titulo = objectdatos.getString("titulo");
                            String descrip = objectdatos.getString("descri");
                            String dis_geo = objectdatos.getString("dis_geo");
                            String estatus = objectdatos.getString("estatus");
                            String historia = objectdatos.getString("historia");
                            String autores = objectdatos.getString("autores");

                            items = new BiodivEnt(id,categoria,titulo,descrip,dis_geo,estatus,historia,autores);
                            itemsArrayList.add(items);
                            adapBiodiv.notifyDataSetChanged();

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_parq", id_parq);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void registrar(int pos, String idparq,String iddiv){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registrando...");
        progressDialog.show();

            StringRequest request=new StringRequest(Request.Method.POST, "https://biodivparques.000webhostapp.com/reg_relacion.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("datos insertados")) {
                        Toast.makeText(RelacionBiodiv.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        startActivity(new Intent(getApplicationContext(), Relacion.class).
                                putExtra("position", pos));

                    } else {
                        Toast.makeText(RelacionBiodiv.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(RelacionBiodiv.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params=new HashMap<>();
                    params.put("id_parq",idparq);
                    params.put("id_div",iddiv);

                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(RelacionBiodiv.this);
            requestQueue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}