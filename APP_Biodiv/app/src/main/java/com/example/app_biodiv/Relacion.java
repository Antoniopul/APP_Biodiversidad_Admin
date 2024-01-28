package com.example.app_biodiv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Relacion extends AppCompatActivity {
    ListView listbiorel;
    FloatingActionButton buttonadd;

    AdapBiodiv adapBiodiv;

    int position;
    String id;
    public static ArrayList<BiodivEnt> itemsArrayList=new ArrayList<>();
    BiodivEnt items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relacion);
            buttonadd=(FloatingActionButton) findViewById(R.id.buttonaddrel);
            listbiorel=(ListView) findViewById(R.id.listrelacion);

                Intent intent = getIntent();
                position = intent.getExtras().getInt("position");
                id = RelacionParq.parquesArrayList.get(position).getId();
                ObtenerDatos(id);

        adapBiodiv =new AdapBiodiv(this , itemsArrayList);
        listbiorel.setAdapter(adapBiodiv);

            listbiorel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int positiondell, long l) {
                    AlertDialog.Builder alertDialog= new AlertDialog.Builder(Relacion.this);
                    alertDialog.setMessage("¿Desea eliminar esta relación?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String idbiodiv=Relacion.itemsArrayList.get(positiondell).getId();
                                    dell(id,idbiodiv);
                                    finish();
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog titulo=alertDialog.create();
                    titulo.setTitle("Eliminar Relacion con Biodividersidad");
                    titulo.show();
                }
            });

            buttonadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String[] datos=new String[]{id, String.valueOf(position)};
                    Bundle bundle=new Bundle();
                    bundle.putStringArray("id",datos);
                    Intent intent=new  Intent(getApplicationContext(), RelacionBiodiv.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

    }

    private void  ObtenerDatos(String id_parque){
        String URL="https://biodivparques.000webhostapp.com/selec_relacion.php";
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
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                params.put("id",id_parque);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void dell(final String idparque, String idbiodiv){
        String URL="https://biodivparques.000webhostapp.com/del_relacion.php";
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("succes")) {
                    Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), RelacionParq.class));
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
                params.put("idpar", idparque);
                params.put("iddiv", idbiodiv);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}