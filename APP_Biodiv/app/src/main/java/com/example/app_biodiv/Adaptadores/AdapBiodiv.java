package com.example.app_biodiv.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_biodiv.Entidades.BiodivEnt;
import com.example.app_biodiv.R;

import java.util.List;

public class AdapBiodiv extends ArrayAdapter<BiodivEnt> {
    Context contexto;
    List<BiodivEnt> itemsList;

    public AdapBiodiv(@NonNull Context contexto, List<BiodivEnt> itemsList){
        super(contexto, R.layout.list_bio, itemsList);
        this.contexto=contexto;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public View getView( int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bio, null, true);
        TextView txttitulo=view.findViewById(R.id.txtlistparq);
        TextView txtcat=view.findViewById(R.id.txtlistbiocat);
        txttitulo.setText(itemsList.get(position).getTitulo());
        txtcat.setText(itemsList.get(position).getCategoria());
        return view;
    }
}
