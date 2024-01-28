package com.example.app_biodiv.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_biodiv.Entidades.MenuEnt;
import com.example.app_biodiv.Entidades.ParqueEnt;
import com.example.app_biodiv.R;

import java.util.ArrayList;
import java.util.List;

public class AdapMenu  extends ArrayAdapter<MenuEnt> {
    Context contexto;
    private int resource;

    public AdapMenu(@NonNull Context contexto, int resource, @NonNull ArrayList<MenuEnt> objects){
        super(contexto, resource, objects);
        this.contexto=contexto;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(contexto);
        convertView=layoutInflater.inflate(resource,parent,false);
        ImageView img=convertView.findViewById(R.id.menuimg);
        TextView txttitulo=convertView.findViewById(R.id.menutit);
        TextView txtdesc=convertView.findViewById(R.id.menudesc);

        img.setImageResource(getItem(position).getImgaen());
        txttitulo.setText(getItem(position).getTitutlo());
        txtdesc.setText(getItem(position).getDescripcion());
        return convertView;
    }
}
