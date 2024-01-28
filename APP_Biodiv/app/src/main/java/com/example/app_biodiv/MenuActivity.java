package com.example.app_biodiv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.app_biodiv.Adaptadores.AdapMenu;
import com.example.app_biodiv.Entidades.MenuEnt;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    ListView listamenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        cardparques=(CardView) findViewById(R.id.cardParques);
//        cardanimales=(CardView) findViewById(R.id.cardBiodiv);
//        cardfotos=(CardView) findViewById(R.id.cardFotos);
//        cardadmins=(CardView) findViewById(R.id.cardAdmins);
        listamenu=(ListView)findViewById(R.id.listmenu);

        ArrayList<MenuEnt> listItems=new ArrayList<>();
        listItems.add(new MenuEnt(R.drawable.admin_ping,"Adminstradores", "Editar y eliminar administradores"));
        listItems.add(new MenuEnt(R.drawable.irekua,"Parques", "Resgistrar y editar parques"));
        listItems.add(new MenuEnt(R.drawable.ave,"Biodiversidad", "Registrar y editar especies"));
        listItems.add(new MenuEnt(R.drawable.relacionar,"Relaciones", "Relacionar especies y parques"));
        listItems.add(new MenuEnt(R.drawable.especies,"Fotografias de Biodiversidad", "Subir o eliminar fotos"));
        listItems.add(new MenuEnt(R.drawable.parque,"Fotografias de Parques", "Subir o eliminar fotos"));

        AdapMenu adaptador=new AdapMenu(this,R.layout.list_menu, listItems);
        listamenu.setAdapter(adaptador);

        listamenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    Intent intent=new Intent(MenuActivity.this, Administrador.class);
                    startActivity(intent);
                }
                else if (i==1){
                    Intent intent=new Intent(MenuActivity.this, Parque.class);
                    startActivity(intent);
                }
                else if (i==2){
                    Intent intent=new Intent(MenuActivity.this, Biodiv.class);
                    startActivity(intent);
                }
                else if (i==3){
                    Intent intent=new Intent(MenuActivity.this, RelacionParq.class);
                    startActivity(intent);
                }
                else  if(i==4){
                    Intent intent=new Intent(MenuActivity.this, Fotos_Bio.class);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(MenuActivity.this, Fotos_Parq.class);
                    startActivity(intent);
                }

            }
        });
    }
}