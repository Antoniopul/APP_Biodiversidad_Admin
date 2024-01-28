package com.example.app_biodiv.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_biodiv.Entidades.BiodivEnt;
import com.example.app_biodiv.Fotos_Bio;
import com.example.app_biodiv.R;

import java.util.List;

public class AdapRecyBio extends RecyclerView.Adapter<AdapRecyBio.PlayViewHolder> {
    private List<BiodivEnt> EntList;
    private ItemClickListenerBio itemClickListener;


    public AdapRecyBio(Fotos_Bio fotos_bio, List<BiodivEnt> EntList, ItemClickListenerBio itemClickListener) {
        this.EntList = EntList;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public PlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bio, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new AdapRecyBio.PlayViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapRecyBio.PlayViewHolder holder, int position) {
        holder.nom.setText(EntList.get(position).getTitulo());
        holder.cat.setText(EntList.get(position).getTitulo());
        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClick(EntList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return EntList.size();
    }

    public interface ItemClickListenerBio{
        void onItemClick(BiodivEnt details);
    }

    class PlayViewHolder extends RecyclerView.ViewHolder {
        TextView nom, cat;

        public PlayViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.txtlistparq);
            cat=itemView.findViewById(R.id.txtlistbiocat);
        }
    }
}