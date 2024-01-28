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

public class Parque_Edit extends AppCompatActivity {
    FloatingActionButton btneditparque;
    TextView nombre,historia,area,perim,calle,
            col,muni,estado,latit,longit,colind,recreo;

    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parque_edit);
        btneditparque=(FloatingActionButton) findViewById(R.id.btneditParque);
        nombre=(TextView)findViewById(R.id.txtnombreparque);
        historia=(TextView)findViewById(R.id.txthistoriaparque);
        area=(TextView)findViewById(R.id.txtarea);
        perim=(TextView)findViewById(R.id.txtperim);
        calle=(TextView)findViewById(R.id.txtcalle);
        col=(TextView)findViewById(R.id.txtcolonia);
        muni=(TextView)findViewById(R.id.txtmunicipio);
        estado=(TextView)findViewById(R.id.txtestado);
        latit=(TextView)findViewById(R.id.txtlatitud);
        longit=(TextView)findViewById(R.id.txtlongitud);
        colind=(TextView)findViewById(R.id.txtcolindancias);
        recreo=(TextView)findViewById(R.id.txtrecreo);

        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");

        nombre.setText(Parque.parquesArrayList.get(position).getNombre());
        historia.setText(Parque.parquesArrayList.get(position).getHistoria());
        area.setText(Parque.parquesArrayList.get(position).getArea());
        perim.setText(Parque.parquesArrayList.get(position).getPerim());
        calle.setText(Parque.parquesArrayList.get(position).getCalle());
        col.setText(Parque.parquesArrayList.get(position).getCol());
        muni.setText(Parque.parquesArrayList.get(position).getMuni());
        estado.setText(Parque.parquesArrayList.get(position).getEstado());
        latit.setText(Parque.parquesArrayList.get(position).getLatit());
        longit.setText(Parque.parquesArrayList.get(position).getLongit());
        colind.setText(Parque.parquesArrayList.get(position).getColind());
        recreo.setText(Parque.parquesArrayList.get(position).getRecreo());

        btneditparque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(Parque_Edit.this);
                alertDialog.setMessage("¿Desea actualizar los datos?")
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

    }

    public void actualizar(){
        String id = Parque.parquesArrayList.get(position).getId();

        String URL="https://biodivparques.000webhostapp.com/edit_parque.php";
        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("succes")){
                    //Toast.makeText(Parque_Edit.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Parque.class));
                    finish();

                }else{
                    Toast.makeText(Parque_Edit.this, "Error al Actalizar", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Parque_Edit.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("id", id);
                params.put("titulo",nombre.getText().toString());
                params.put("historia", historia.getText().toString());
                params.put("area", area.getText().toString());
                params.put("perim", perim.getText().toString());
                params.put("calle", calle.getText().toString());
                params.put("col", col.getText().toString());
                params.put("municip", muni.getText().toString());
                params.put("estado", estado.getText().toString());
                params.put("latit", latit.getText().toString());
                params.put("long", longit.getText().toString());
                params.put("colind", colind.getText().toString());
                params.put("recreo", recreo.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(Parque_Edit.this);
        requestQueue.add(request);
    }

//    private void dell(final String nombre){
//        String URL="https://biodivparques.000webhostapp.com/del_parque.php";
//        StringRequest request=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (response.equalsIgnoreCase("succes")) {
//                    Toast.makeText(Parque_Edit.this, "Eliminado", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), Parques.class));
//                } else {
//                    Toast.makeText(Parque_Edit.this, "Error al Eliminar", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Parque_Edit.this, "Error al Eliminar", Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String>params=new HashMap<>();
//                params.put("nombre", nombre);
//                return params;
//            }
//        };
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        requestQueue.add(request);
//    }
}