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
import com.example.app_biodiv.Adaptadores.AdapRecyBio;
import com.example.app_biodiv.Entidades.BiodivEnt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fotos_Bio extends AppCompatActivity {
    RecyclerView recybio;

    List<BiodivEnt> entidadItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_bio);
        recybio = (RecyclerView) findViewById(R.id.recicbiofoto);

        recybio.setHasFixedSize(true);
        recybio.setLayoutManager(new LinearLayoutManager(this));
        entidadItems = new ArrayList<>();

        ObtenerDatos();
    }

    private void ObtenerDatos() {
        String URL = "https://biodivparques.000webhostapp.com/selec_biodiv.php";
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

                            entidadItems.add(new BiodivEnt(
                                    objectdatos.getString("id"),
                                    objectdatos.getString("categoria"),
                                    objectdatos.getString("titulo"),
                                    objectdatos.getString("descri"),
                                    objectdatos.getString("dis_geo"),
                                    objectdatos.getString("estatus"),
                                    objectdatos.getString("historia"),
                                    objectdatos.getString("autores")
                            ));
                        }
                        AdapRecyBio adapRecyBio =new AdapRecyBio(Fotos_Bio.this, entidadItems, new AdapRecyBio.ItemClickListenerBio() {
                            @Override
                            public void onItemClick(BiodivEnt details) {
                                String[] datos=new String[]{details.getId()};
                                Bundle bundle=new Bundle();
                                bundle.putStringArray("id",datos);
                                Intent intent=new  Intent(getApplicationContext(), Fotos_Bio_Rel.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                        recybio.setAdapter(adapRecyBio);
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
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
