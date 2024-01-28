package com.example.app_biodiv.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_biodiv.Entidades.ParqueEnt;
import com.example.app_biodiv.Fotos_Parq;
import com.example.app_biodiv.R;

import java.util.List;

public class AdapRecyPar extends  RecyclerView.Adapter<AdapRecyPar.PlayViewHolder> {
private List<ParqueEnt> parqueEnts;
private ItemClickListenerPar itemClickListener;

    public AdapRecyPar(Fotos_Parq fotos_parq, List<ParqueEnt> parqueEnts, AdapRecyPar.ItemClickListenerPar itemClickListener) {
        this.parqueEnts = parqueEnts;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public AdapRecyPar.PlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_parq, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new AdapRecyPar.PlayViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapRecyPar.PlayViewHolder holder, int position) {
        holder.nom.setText(parqueEnts.get(position).getNombre());
        holder.estad.setText(parqueEnts.get(position).getEstado());
        holder.munic.setText(parqueEnts.get(position).getMuni());
        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(parqueEnts.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return parqueEnts.size();
    }

    public interface ItemClickListenerPar{
        void onItemClick(ParqueEnt details);
    }

    class PlayViewHolder extends RecyclerView.ViewHolder {
        TextView nom, estad, munic;
        public PlayViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.txtlistparq);
            estad=itemView.findViewById(R.id.txtlistparestad);
            munic=itemView.findViewById(R.id.txtlistparqmuni);
        }
    }
}
