package com.example.app_biodiv.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_biodiv.Entidades.Admins;
import com.example.app_biodiv.R;

import java.util.List;

public class AdapAdmin extends ArrayAdapter<Admins>{
    Context contexto;
    List<Admins> adminsList;

    public AdapAdmin(@NonNull Context contexto, List<Admins> adminsList){
        super(contexto, R.layout.list_admin, adminsList);
        this.contexto=contexto;
        this.adminsList = adminsList;
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_admin, null, true);
        TextView txtadmin=view.findViewById(R.id.txtadmview);
        txtadmin.setText(adminsList.get(position).getUsuario());
        return view;
    }
}

