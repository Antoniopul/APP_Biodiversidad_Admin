package com.example.app_biodiv.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.app_biodiv.Entidades.ParqueEnt;
import com.example.app_biodiv.R;

import java.util.List;

public class AdapParque extends ArrayAdapter<ParqueEnt> {
    Context contexto;
    List<ParqueEnt> parquesList;

    public AdapParque(@NonNull Context contexto, List<ParqueEnt> parquesList){
        super(contexto, R.layout.list_parq, parquesList);
        this.contexto=contexto;
        this.parquesList = parquesList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_parq, null, true);
        TextView txttitulo=view.findViewById(R.id.txtlistparq);
        TextView txtmunic=view.findViewById(R.id.txtlistparqmuni);
        TextView txtestad=view.findViewById(R.id.txtlistparestad);

        txttitulo.setText(parquesList.get(position).getNombre());
        txtmunic.setText(parquesList.get(position).getMuni());
        txtestad.setText(parquesList.get(position).getEstado());
        return view;
    }
}