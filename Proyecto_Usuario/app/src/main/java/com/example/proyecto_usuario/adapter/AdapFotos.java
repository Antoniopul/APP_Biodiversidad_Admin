package com.example.proyecto_usuario.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.proyecto_usuario.R;
import com.example.proyecto_usuario.models.FotosEnt;


import java.util.List;

public class AdapFotos extends RecyclerView.Adapter<AdapFotos.PlayViewHolder>  {
private Context context;
private List<FotosEnt> fotosEntList;
private ItemClickListenerFoto itemClickListener;
public AdapFotos(Context context,List<FotosEnt>fotosEntList, ItemClickListenerFoto itemClickListener){
        this.context=context;
        this.fotosEntList=fotosEntList;
        this.itemClickListener=itemClickListener;
        }

@NonNull
@Override
public PlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imagenes, parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new PlayViewHolder(vista);
        }

@Override
public void onBindViewHolder(@NonNull PlayViewHolder holder, int position) {
        FotosEnt fotosEnt=fotosEntList.get(position);
        Glide.with(context)
        .load(fotosEnt.getImagen())
        .into(holder.img);
        holder.itemView.setOnClickListener(view -> {
        itemClickListener.onItemClick(fotosEntList.get(position));
        });
        }

@Override
public int getItemCount() {
        return fotosEntList.size();
        }

public interface ItemClickListenerFoto{
    void onItemClick(FotosEnt details);
}


class PlayViewHolder extends RecyclerView.ViewHolder{
    ImageView img;
    public PlayViewHolder(@NonNull View itemView){
        super(itemView);
        img=itemView.findViewById(R.id.imageskip);
    }
}

}
