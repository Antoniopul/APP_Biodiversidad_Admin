package com.example.app_biodiv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class Administrador_EditDell extends AppCompatActivity {
    FloatingActionButton btneditadmin, btndeladmin;
    TextView txtuser, txtpass;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_edit_dell);
        btneditadmin=(FloatingActionButton) findViewById(R.id.btneditAdmin);
        btndeladmin=(FloatingActionButton) findViewById(R.id.btndelAdmin);
        txtuser=(TextView)findViewById(R.id.txtusuernam);
        txtpass=(TextView)findViewById(R.id.txtpassreg);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        txtuser.setText(Administrador.adminsArrayList.get(position).getUsuario());
        txtpass.setText(Administrador.adminsArrayList.get(position).getPass());

        btneditadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(Administrador_EditDell.this);
                alertDialog.setMessage("¿Desea actualizar los datos del Administrador?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                actualizar();
                                finish();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo=alertDialog.create();
                titulo.setTitle("Actualizar Datos");
                titulo.show();


            }
        });

        btndeladmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(Administrador_EditDell.this);
                alertDialog.setMessage("¿Desea eliminar este Administrador?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dell(txtuser.getText().toString());
                                finish();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo=alertDialog.create();
                titulo.setTitle("Eliminar Adminstrador");
                titulo.show();

            }
        });
    }

    public void actualizar(){
        String id = Administrador.adminsArrayList.get(position).getId();

        String URL="https://biodivparques.000webhostapp.com/edit_admin.php";
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("succes")){
                    //Toast.makeText(Administradores_EditDell.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Administrador.class));
                    finish();

                }else{
                    Toast.makeText(Administrador_EditDell.this, "Error al Actalizar", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Administrador_EditDell.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String>params=new HashMap<>();
               params.put("id", id);
               params.put("user", txtuser.getText().toString());
               params.put("pass", txtpass.getText().toString());
               return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(Administrador_EditDell.this);
        requestQueue.add(request);
    }

    private void dell(final String user){

        String URL="https://biodivparques.000webhostapp.com/del_admin.php";
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("succes")) {
                    //Toast.makeText(Administradores_EditDell.this, "Eliminado", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Administrador.class));

                } else {
                    Toast.makeText(Administrador_EditDell.this, "Error al Eliminar", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Administrador_EditDell.this, "Error al Eliminar", Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("user", user);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}