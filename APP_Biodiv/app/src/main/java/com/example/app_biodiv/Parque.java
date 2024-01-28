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
import com.example.app_biodiv.Adaptadores.AdapParque;
import com.example.app_biodiv.Entidades.ParqueEnt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parque extends AppCompatActivity {
    FloatingActionButton regparques;
    ListView listparques;

    AdapParque adapParque;

    public static ArrayList<ParqueEnt>parquesArrayList=new ArrayList<>();
    ParqueEnt parques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parque);
        regparques=(FloatingActionButton) findViewById(R.id.ActbtnregParques);
        listparques=(ListView) findViewById(R.id.listParques);

        adapParque=new AdapParque(this, parquesArrayList );
        listparques.setAdapter(adapParque);

        listparques.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(), Parque_Edit.class).
                        putExtra("position",position));
            }
        });

        regparques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Parque.this, Parques_Reg.class);
                startActivity(intent);
            }
        });
        ObtenerDatos();
    }

    private void  ObtenerDatos(){
        String URL="https://biodivparques.000webhostapp.com/selec_parque.php";
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parquesArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if (succes.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject objectdatos = jsonArray.getJSONObject(i);

                            String id=objectdatos.getString("id");
                            String nombre = objectdatos.getString("titulo");
                            String historia=objectdatos.getString("historia");
                            String area=objectdatos.getString("area");
                            String perim=objectdatos.getString("perim");
                            String calle=objectdatos.getString("calle");
                            String col=objectdatos.getString("col");
                            String muni=objectdatos.getString("municip");
                            String estado=objectdatos.getString("estado");
                            String latit=objectdatos.getString("latit");
                            String longit=objectdatos.getString("long");
                            String colind=objectdatos.getString("colind");
                            String recreo=objectdatos.getString("recreo");

                            parques = new ParqueEnt(id,nombre,historia,area,perim,calle,
                                    col,muni,estado,latit,longit,colind,recreo);
                            parquesArrayList.add(parques);
                            adapParque.notifyDataSetChanged();

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