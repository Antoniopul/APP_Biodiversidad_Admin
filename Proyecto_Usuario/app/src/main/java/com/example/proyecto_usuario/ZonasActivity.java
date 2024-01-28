package com.example.proyecto_usuario;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_usuario.adapter.TripsAdapter;
import com.example.proyecto_usuario.models.Description;
import com.example.proyecto_usuario.models.Item;
import com.example.proyecto_usuario.models.Trip;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZonasActivity extends AppCompatActivity {

    private TripsAdapter mAdapter;
    public static List<Item> item = new ArrayList<>();
    TripsAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas);


        recyclerView = findViewById(R.id.recyclerView);


        consParque("https://biodivparques.000webhostapp.com/selec_parque.php");

        /*
        Trip trip1 = new Trip(R.drawable.centro, "titulo", "estado" + ", " + "municip");
        item.add(new Item(0, trip1));

        Description desc1 = new Description("municip", "col", "calle", "colind", "area", "perim", "latit", "long", "recreo", "historia");
        item.add(new Item(1, desc1));

        adapter = new TripsAdapter(item);
        recyclerView.setAdapter(adapter);

        adapter.OnItemViewClickListener(new TripsAdapter.OnItemViewClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(ZonasActivity.this, "position" + position, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ZonasActivity.this, GaleriaActivity.class);
                startActivity(intent);

            }
        });

         */


    }


    private void consParque(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String exito = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");

                    if (exito.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            Trip trip1 = new Trip(R.drawable.centro, obj.getString("titulo"), (obj.getString("estado") + ", " + obj.getString("municip")));
                            item.add(new Item(0, trip1));

                            Description desc1 = new Description(obj.getString("municip"), obj.getString("col"), obj.getString("calle"), obj.getString("colind"), obj.getString("area"), obj.getString("perim"), obj.getString("latit"), obj.getString("long"), obj.getString("recreo"), obj.getString("historia"));
                            item.add(new Item(1, desc1));
                        }

                        adapter = new TripsAdapter(item);
                        recyclerView.setAdapter(adapter);

                        adapter.OnItemViewClickListener(new TripsAdapter.OnItemViewClickListener() {
                            @Override
                            public void OnItemClick(int position) {
                                //Toast.makeText(ZonasActivity.this, "position" + position, Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), GaleriaActivity.class).
                                        putExtra("position",position));

                            }
                        });
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR CONEXION", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}