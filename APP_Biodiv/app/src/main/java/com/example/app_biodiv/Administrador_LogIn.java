package com.example.app_biodiv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class Administrador_LogIn extends AppCompatActivity {
    EditText edittxtname, edittxtpass;
    Button btnsingup, btnRegis;

    RequestQueue request;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_login);

        edittxtname=findViewById(R.id.edittxtname);
        edittxtpass=findViewById(R.id.edittxtpass);
        btnRegis=findViewById(R.id.Register);
        btnsingup=findViewById(R.id.singUP);

        btnsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarSesion();
            }
        });

       btnRegis.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               Intent intent=new Intent(Administrador_LogIn.this, Administrador_Reg.class);
               startActivity(intent);
           }
       });
    }

    private void IniciarSesion(){
        request=Volley.newRequestQueue(this);
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Logueando...");
        progressDialog.show();
        String url="https://biodivparques.000webhostapp.com/val_usuario.php?user="+edittxtname.getText().toString();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    String pass = jsonArray.getString(0);
                    if (pass.equals(edittxtpass.getText().toString())) {
                        //Toast.makeText(getApplicationContext(), "Logueado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                        finish();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(getApplicationContext(), "Datos Incorrectos", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "No existe el Usuario", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });request.add(stringRequest);
    }
}