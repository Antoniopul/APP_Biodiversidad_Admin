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
import com.example.proyecto_usuario.adapter.FotoAdapter;
import com.example.proyecto_usuario.adapter.ImagesAdapter;
import com.example.proyecto_usuario.models.Foto;
import com.example.proyecto_usuario.models.FotosEnt;
import com.example.proyecto_usuario.models.Imagenes;
import com.example.proyecto_usuario.models.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesplimgActivity extends AppCompatActivity {

    ImageView imagenprincipal;
    List<FotosEnt> fotosEntList;
    RecyclerView recyclerView;

    public int[] imagenesArray = {
            R.drawable.azulejo,
            R.drawable.mordecai,
            R.drawable.rubg
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desplimg);

        imagenprincipal = findViewById(R.id.imagev_Principal);

        recyclerView = findViewById(R.id.recycler_Despimage);

        //List<Item> item = new ArrayList<>();
        Intent i = getIntent();
        String id = i.getExtras().getString("id");
        ObtenerDatos(id);

        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fotosEntList=new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(DesplimgActivity.this, LinearLayoutManager.HORIZONTAL, false));

        /*
        Imagenes foto1 = new Imagenes(imagenesArray[0]);
        item.add(new Item(1, foto1));

        Imagenes foto2 = new Imagenes(imagenesArray[1]);
        item.add(new Item(1, foto2));

        Imagenes foto3 = new Imagenes(imagenesArray[2]);
        item.add(new Item(1, foto3));

        ImagesAdapter adapter = new ImagesAdapter(item);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DesplimgActivity.this, LinearLayoutManager.HORIZONTAL, false));

        adapter.OnItemViewClickListener(new ImagesAdapter.OnImageViewClickListener() {
            @Override
            public void OnImageClick(int position) {
                imagenprincipal.setImageResource(imagenesArray[position]);;
            }
        });

         */
    }
    private void  ObtenerDatos(String id_bio){
        String URL="https://biodivparques.000webhostapp.com/selec_foto_bio.php";
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
                        AdapFotos adapFotos=new AdapFotos(DesplimgActivity.this, fotosEntList, new AdapFotos.ItemClickListenerFoto() {
                            @Override
                            public void onItemClick(FotosEnt details) {
                                imagenprincipal.setImageResource(imagenesArray[0]);;
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
                params.put("id_biodiv", id_bio);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}