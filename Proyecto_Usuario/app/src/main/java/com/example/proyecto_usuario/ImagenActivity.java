package com.example.proyecto_usuario;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImagenActivity extends AppCompatActivity {
    ImageView imageviewdetalle;
    TextView textvtitulo;
    TextView textvdesc;
    TextView textvaut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        imageviewdetalle = findViewById(R.id.Imagev_detalle);
        textvtitulo = findViewById(R.id.Txtv_titulo);
        textvaut = findViewById(R.id.Txtv_autores);
        textvdesc = findViewById(R.id.Txtv_desc);

        Intent i = getIntent();
        int posicion = i.getExtras().getInt("position");
        String id = i.getExtras().getString("id");

        consparqueespecies("https://biodivparques.000webhostapp.com/selec_bio_espesif.php", id);
        consfotobiov("https://biodivparques.000webhostapp.com/selec_foto_bio.php", id);

        imageviewdetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ImagenActivity.this, DesplimgActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
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
                            String categoria = obj.getString("categoria");
                            String titulo = obj.getString("titulo");
                            String descrip = obj.getString("descri");
                            String dis_geo = obj.getString("dis_geo");
                            String estatus = obj.getString("estatus");
                            String historia = obj.getString("historia");
                            String autores = obj.getString("autores");

                            textvtitulo.setText(titulo);
                            textvaut.setText(autores);
                            textvdesc.setText(Html.fromHtml("<b>Categoria: </b>" + categoria + "<br>" +
                                    "<b>Descripcion: </b>" + descrip + "<br>" +
                                    "<b>Distribución geografica: </b>" + dis_geo + "<br>" +
                                    "<b>Historia: </b>" + historia + "<br>" +
                                    "<b>Estatus de conservacion: </b>" + estatus));
                        }

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

    private void consfotobiov (String URL, String id){
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
                            String foto = obj.getString("foto");

                            Picasso.get().load(foto).resize(430, 234).into(imageviewdetalle);
                        }

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
                params.put("id_biodiv", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}