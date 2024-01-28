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
import com.example.app_biodiv.Adaptadores.AdapAdmin;
import com.example.app_biodiv.Entidades.Admins;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Administrador extends AppCompatActivity {
    ListView listadmins;
    AdapAdmin adapter;

    public static ArrayList<Admins>adminsArrayList=new ArrayList<>();
    Admins admins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        listadmins=(ListView) findViewById(R.id.listviwAdmins);

        adapter=new AdapAdmin(this, adminsArrayList);
        listadmins.setAdapter(adapter);

        listadmins.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent, View view,final int position, long id) {
                startActivity(new Intent(getApplicationContext(), Administrador_EditDell.class).
                        putExtra("position",position));
            }
        });
        ObtenerDatos();
    }

    private void  ObtenerDatos(){
        String URL="https://biodivparques.000webhostapp.com/selec_admins.php";
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                adminsArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("succes");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if (succes.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject objectdatos = jsonArray.getJSONObject(i);

                            String id=objectdatos.getString("id");
                            String nombre = objectdatos.getString("nombre");
                            String pass = objectdatos.getString("passw");

                            admins = new Admins(id,nombre,pass);
                            adminsArrayList.add(admins);
                            adapter.notifyDataSetChanged();
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