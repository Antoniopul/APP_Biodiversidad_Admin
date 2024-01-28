package com.example.proyecto_usuario.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_usuario.R;
import com.example.proyecto_usuario.models.Foto;
import com.example.proyecto_usuario.models.Imagenes;
import com.example.proyecto_usuario.models.Item;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Item> items;

    public ImagesAdapter(List<Item> items) {
        this.items = items;
    }

    private ImagesAdapter.OnImageViewClickListener listener;

    public interface  OnImageViewClickListener{
        void OnImageClick(int position);
    }

    public void OnItemViewClickListener(ImagesAdapter.OnImageViewClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        return new ImagesViewHolder(
                (itemView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_imagenes,
                        parent,
                        false)
                ), listener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Imagenes imagenes = (Imagenes) items.get(position).getObject();
        ((ImagesAdapter.ImagesViewHolder) holder).setSkipImage(imagenes);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    static class ImagesViewHolder extends RecyclerView.ViewHolder{

        private ImageView skipImage;

        public ImagesViewHolder(@NonNull View itemView, ImagesAdapter.OnImageViewClickListener listener) {
            super(itemView);
            skipImage = itemView.findViewById(R.id.imageskip);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null && getAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.OnImageClick(getAdapterPosition());
                    }
                }
            });

        }

        void setSkipImage (Imagenes imagenes){
            skipImage.setImageResource((imagenes.getSkipImage()));
        }

    }

}
