package com.example.app_biodiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_biodiv.Adaptadores.AdapBiodiv;
import com.example.app_biodiv.Entidades.BiodivEnt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Biodiv extends AppCompatActivity {
    FloatingActionButton reganimales;
    ListView listbiodiv;

    AdapBiodiv adapBiodiv;

    public static ArrayList<BiodivEnt>itemsArrayList=new ArrayList<>();
    BiodivEnt items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodiv);
        reganimales=(FloatingActionButton) findViewById(R.id.ActbtnregAnimales);
        listbiodiv=(ListView) findViewById(R.id.listBiodiv);

        adapBiodiv =new AdapBiodiv(this , itemsArrayList);
        listbiodiv.setAdapter(adapBiodiv);

        listbiodiv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), Biodiv_Edit.class).
                        putExtra("position",position));
            }
        });

        reganimales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Biodiv.this, Biodiv_Reg.class);
                startActivity(intent);
            }
        });
        ObtenerDatos();
    }

    private void  ObtenerDatos(){
        String URL="https://biodivparques.000webhostapp.com/selec_biodiv.php";
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
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}