package com.example.proyecto_usuario.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_usuario.R;
import com.example.proyecto_usuario.models.Description;
import com.example.proyecto_usuario.models.Foto;
import com.example.proyecto_usuario.models.Item;
import com.example.proyecto_usuario.models.Trip;

import java.util.List;

public class FotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> items;
    public FotoAdapter(List<Item> items) {
        this.items = items;
    }

    private FotoAdapter.OnFotoViewClickListener listener;

    public interface  OnFotoViewClickListener{
        void OnItemClick(int position);
    }

    public void OnItemViewClickListener(FotoAdapter.OnFotoViewClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        return new FotoAdapter.FotoViwHolder(
                (itemView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_foto,
                        parent,
                        false)
                ), listener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Foto foto = (Foto) items.get(position).getObject();
        ((FotoAdapter.FotoViwHolder) holder).setFotoData(foto);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    static class FotoViwHolder extends RecyclerView.ViewHolder{
        private ImageView imageFoto;
        private TextView fotoTitle;


        FotoViwHolder(@NonNull View itemView, FotoAdapter.OnFotoViewClickListener listener) {
            super(itemView);
            imageFoto = itemView.findViewById(R.id.imageButtonFoto);
            fotoTitle = itemView.findViewById(R.id.textFoto);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null && getAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.OnItemClick(getAdapterPosition());
                    }
                }
            });
        }

        void setFotoData(Foto foto){
            imageFoto.setImageResource(foto.getFotoImage());
            fotoTitle.setText(foto.getFotoTitle());
        }
    }

}
