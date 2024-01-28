package com.example.proyecto_usuario;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.proyecto_usuario.adapter.FotoAdapter;
import com.example.proyecto_usuario.adapter.TripsAdapter;
import com.example.proyecto_usuario.models.Description;
import com.example.proyecto_usuario.models.Foto;
import com.example.proyecto_usuario.models.Item;
import com.example.proyecto_usuario.models.Trip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GaleriaActivity extends AppCompatActivity {

    List<Item> item = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton btngalzonas;
    FotoAdapter adapter;

    int position;
    String id;
    List<String> Biodiv = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        btngalzonas = (FloatingActionButton) findViewById(R.id.floatbutt_zonas);
        recyclerView = findViewById(R.id.recyclerFoto);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        //id = ZonasActivity.item.get(position).getObject();
        if (position == 0) {
            id = String.valueOf(position + 1);
        }
        else{
            id = String.valueOf(position);
        }

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        consparqueespecies("https://biodivparques.000webhostapp.com/selec_relacion.php", id);

        btngalzonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ParquesImgActivity.class).
                        putExtra("id", id));
            }
        });

        /*
        Foto foto1 = new Foto(R.drawable.centro, "Pajaro 1");
        item.add(new Item(1, foto1));

        Foto foto2 = new Foto(R.drawable.centro, "Pajaro 2");
        item.add(new Item(1, foto2));

        Foto foto3 = new Foto(R.drawable.centro, "Pajaro 3");
        item.add(new Item(1, foto3));

        Foto foto4 = new Foto(R.drawable.centro, "Pajaro 4");
        item.add(new Item(1, foto4));
        item.add(new Item(1, foto4));
        item.add(new Item(1, foto4));

        adapter = new FotoAdapter(item);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        adapter.OnItemViewClickListener(new FotoAdapter.OnFotoViewClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(GaleriaActivity.this, "position" + position, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ImagenActivity.class);
                intent.putExtra("idimagen", position);
                startActivity(intent);

            }
        });

         */



    }


    private void consparqueespecies(String URL, String id){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        String exito = jsonObject.getString("succes");
                        JSONArray jsonArray = jsonObject.getJSONArray("datos");

                        if (exito.equals("1")) {

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                Foto foto = new Foto(R.drawable.centro, obj.getString("titulo"));
                                item.add(new Item(1, foto));
                                Biodiv.add(obj.getString("id"));
                            }

                            adapter = new FotoAdapter(item);
                            recyclerView.setAdapter(adapter);

                            adapter.OnItemViewClickListener(new FotoAdapter.OnFotoViewClickListener() {
                                @Override
                                public void OnItemClick(int position) {
                                    //Toast.makeText(GaleriaActivity.this, "position" + position, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(),ImagenActivity.class);
                                    intent.putExtra("position", position);
                                    intent.putExtra("id", Biodiv.get(position));
                                    Toast.makeText(GaleriaActivity.this, "position" + Biodiv.get(position), Toast.LENGTH_LONG).show();
                                    startActivity(intent);

                                }
                            });
                        }
                    }
                    catch (JSONException e){
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR CONEXION", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}