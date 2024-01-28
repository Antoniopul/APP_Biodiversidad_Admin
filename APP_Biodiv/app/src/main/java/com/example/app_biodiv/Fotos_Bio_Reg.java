package com.example.app_biodiv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class Fotos_Bio_Reg extends AppCompatActivity {

    Button btnBuscar;
    Button btnSubir;
    ImageView imageView;
    EditText editTextName, txtautor;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_bio_reg);
        btnBuscar=(Button) findViewById(R.id.btnBuscar);
        btnSubir=(Button) findViewById(R.id.btnSubir);
        imageView=(ImageView) findViewById(R.id.imagenFoto);
        editTextName=(EditText) findViewById(R.id.nombrefoto);
        txtautor=(EditText)findViewById(R.id.txtautorfoto);

        Bundle obtener=getIntent().getExtras();
        String[] id=obtener.getStringArray("id");

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadImage(id[0]);
            }
        });
    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(String id_bio){
        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);

        String URL ="https://biodivparques.000webhostapp.com/subir_fot_bio.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(Fotos_Bio_Reg.this, s , Toast.LENGTH_LONG).show();

                        String[] datos=new String[]{id_bio};
                        Bundle bundle=new Bundle();
                        bundle.putStringArray("id",datos);
                        Intent intent=new  Intent(getApplicationContext(), Fotos_Bio_Rel.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(Fotos_Bio_Reg.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                //Obtener el nombre de la imagen
                String nombre = editTextName.getText().toString().trim();

                String autor=txtautor.getText().toString().trim();

                //Creación de parámetros
                Map<String,String> params = new Hashtable<String,String>();

                //Agregando de parámetros
                params.put("id",id_bio);
                params.put("foto", imagen);
                params.put("nombre", nombre);
                params.put("autores",autor);

                //Parámetros de retorno
                return params;
            }
        };

        //Creación de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Imagen"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}