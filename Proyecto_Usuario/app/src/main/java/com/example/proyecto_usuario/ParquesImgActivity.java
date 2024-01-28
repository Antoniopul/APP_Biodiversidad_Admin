package com.example.proyecto_usuario;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_usuario.adapter.AdapFotos;
import com.example.proyecto_usuario.models.FotosEnt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParquesImgActivity extends AppCompatActivity {

    List<FotosEnt> fotosEntList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parques_img);

        recyclerView = findViewById(R.id.recyclerParq);

        Intent i = getIntent();
        String id = i.getExtras().getString("id");
        ObtenerDatos(id);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fotosEntList=new ArrayList<>();
    }

    private void  ObtenerDatos(String id_bio){
        String URL="https://biodivparques.000webhostapp.com/selec_foto_parq.php";
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

                            fotosEntList.add(new FotosEnt(
                                    objectdatos.getString("id"),
                                    objectdatos.getString("id_asos"),
                                    objectdatos.getString("cat"),
                                    objectdatos.getString("foto"),
                                    objectdatos.getString("nombre"),
                                    objectdatos.getString("autor")
                            ));
                        }
                        AdapFotos adapFotos=new AdapFotos(ParquesImgActivity.this, fotosEntList, new AdapFotos.ItemClickListenerFoto() {
                            @Override
                            public void onItemClick(FotosEnt details) {

                            }
                        });
                        recyclerView.setAdapter(adapFotos);

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
                params.put("id_parq", id_bio);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}