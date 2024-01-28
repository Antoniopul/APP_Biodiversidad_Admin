package com.example.app_biodiv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_biodiv.Adaptadores.AdapRecyPar;
import com.example.app_biodiv.Entidades.ParqueEnt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fotos_Parq extends AppCompatActivity {
    RecyclerView reciparq;

    List<ParqueEnt> parqueEnts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_parq);
        reciparq=(RecyclerView)findViewById(R.id.recicfotparq);

        reciparq.setHasFixedSize(true);
        reciparq.setLayoutManager(new LinearLayoutManager(this));
        parqueEnts=new ArrayList<>();


        ObtenerDatos();
    }

    private void  ObtenerDatos(){
        String URL="https://biodivparques.000webhostapp.com/selec_parque.php";
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if (succes.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject objectdatos = jsonArray.getJSONObject(i);

                            parqueEnts.add(new ParqueEnt(
                                    objectdatos.getString("id"),
                                    objectdatos.getString("titulo"),
                                    objectdatos.getString("historia"),
                                    objectdatos.getString("area"),
                                    objectdatos.getString("perim"),
                                    objectdatos.getString("calle"),
                                    objectdatos.getString("col"),
                                    objectdatos.getString("municip"),
                                    objectdatos.getString("estado"),
                                    objectdatos.getString("latit"),
                                    objectdatos.getString("long"),
                                    objectdatos.getString("colind"),
                                    objectdatos.getString("recreo")
                            ));
                        }
                        AdapRecyPar adapRecyPar =new AdapRecyPar(Fotos_Parq.this, parqueEnts, new AdapRecyPar.ItemClickListenerPar() {
                            @Override
                            public void onItemClick(ParqueEnt details) {
                                String[] datos=new String[]{details.getId()};
                                Bundle bundle=new Bundle();
                                bundle.putStringArray("id",datos);
                                Intent intent=new  Intent(getApplicationContext(), Fotos_Parq_Rel.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        reciparq.setAdapter(adapRecyPar);
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
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}