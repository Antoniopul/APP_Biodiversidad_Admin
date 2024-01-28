package com.example.app_biodiv.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_biodiv.Entidades.FotosEnt;
import com.example.app_biodiv.R;

import java.util.List;

public class AdapRecyFotos extends RecyclerView.Adapter<AdapRecyFotos.PlayViewHolder>  {
    private Context context;
    private List<FotosEnt>fotosEntList;
    private ItemClickListenerFoto itemClickListener;
    public AdapRecyFotos(Context context, List<FotosEnt>fotosEntList, ItemClickListenerFoto itemClickListener){
        this.context=context;
        this.fotosEntList=fotosEntList;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public PlayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fotos, parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new PlayViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayViewHolder holder, int position) {
        holder.nom.setText(fotosEntList.get(position).getNombre());
        holder.aut.setText(fotosEntList.get(position).getAutores());
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
        TextView nom, aut;
        ImageView img;
        public PlayViewHolder(@NonNull View itemView){
            super(itemView);
            nom=itemView.findViewById(R.id.txtnomfoto);
            aut=itemView.findViewById(R.id.txtautfoto);
            img=itemView.findViewById(R.id.imglistfoto);
        }
    }

}
