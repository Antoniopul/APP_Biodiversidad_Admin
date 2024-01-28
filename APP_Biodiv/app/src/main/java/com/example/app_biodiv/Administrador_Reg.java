package com.example.app_biodiv;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Administrador_Reg extends AppCompatActivity {
    Button btnregis;
    TextView txtuser, txtpass, confpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_reg);
        btnregis = findViewById(R.id.btnResgist);
        txtuser = findViewById(R.id.txtusuernam);
        txtpass = findViewById(R.id.txtpassreg);
        confpass=(TextView)findViewById(R.id.confpass);

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();
            }
        });
    }
    private void registrar(){
        final String usuario=txtuser.getText().toString().trim();
        final String pass=txtpass.getText().toString().trim();
        final String pass2=confpass.getText().toString().trim();

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registrando...");

        if (usuario.isEmpty()){
            Toast.makeText(this, "Ingrese Nombre",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (pass.isEmpty()){
            Toast.makeText(this, "Ingrese Password",Toast.LENGTH_SHORT).show();
            return;
        }
        else if (pass2.isEmpty()) {
            Toast.makeText(this, "Confirmacion de contraseña vacio", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!pass2.equals(pass)) {
            Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            progressDialog.show();
            StringRequest request=new StringRequest(Request.Method.POST, "https://biodivparques.000webhostapp.com/reg_admins.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("datos insertados")) {
                        Toast.makeText(Administrador_Reg.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        startActivity(new Intent(getApplicationContext(), Administrador_LogIn.class));
                        finish();
                    } else {
                        Toast.makeText(Administrador_Reg.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Administrador_Reg.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params=new HashMap<>();
                    params.put("user",usuario);
                    params.put("pass",pass);
                    return params;
                }
            };
            RequestQueue requestQueue=Volley.newRequestQueue(Administrador_Reg.this);
            requestQueue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}