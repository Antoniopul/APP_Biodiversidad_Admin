package com.example.proyecto_usuario.adapter;

import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_usuario.MainActivity;
import com.example.proyecto_usuario.R;
import com.example.proyecto_usuario.RecyclerViewInterface;
import com.example.proyecto_usuario.ZonasActivity;
import com.example.proyecto_usuario.models.Foto;
import com.example.proyecto_usuario.models.Item;
import com.example.proyecto_usuario.models.Description;
import com.example.proyecto_usuario.models.Trip;
import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Item> items;
    public TripsAdapter(List<Item> items) {
        this.items = items;
    }

    private OnItemViewClickListener listener;

    public interface  OnItemViewClickListener{
        void OnItemClick(int position);
    }

    public void OnItemViewClickListener(OnItemViewClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Trip = 0, Description = 1, foto = 2

        View itemView;

        if(viewType == 0){
            return new TripViwHolder(

                    (itemView = LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_trip,
                            parent,
                            false)
                    ), listener
            );
        }else{
            return new DescViwHolder(

                    itemView = LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.item_descrption,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 0){
            Trip trip = (Trip) items.get(position).getObject();
            ((TripViwHolder) holder).setTripData(trip);
        }
        else {
            Description description = (Description) items.get(position).getObject();
            ((DescViwHolder) holder).setDescData(description);
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }


    static class TripViwHolder extends RecyclerView.ViewHolder{
        private ImageView imageTrip;
        private TextView textTripTitle, textTrip;


        TripViwHolder(@NonNull View itemView, OnItemViewClickListener listener) {
            super(itemView);
            imageTrip = itemView.findViewById(R.id.imageTrip);
            textTripTitle = itemView.findViewById(R.id.textTripTitle);
            textTrip = itemView.findViewById(R.id.textTrip);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null && getAdapterPosition()!=RecyclerView.NO_POSITION){
                        listener.OnItemClick(getAdapterPosition());
                    }
                }
            });
        }

        void setTripData(Trip trip){
            imageTrip.setImageResource(trip.getTripImage());
            textTripTitle.setText(trip.getTripTitle());
            textTrip.setText(trip.getTrip());
        }
    }

    static class DescViwHolder extends RecyclerView.ViewHolder{
        private TextView textDescTitle, textDesc, textDescCalle, textDescColin, textDescArea, textDescPerim, textDescLatit, textDescLong, textDescAreaR, textDescHist;


        DescViwHolder(@NonNull View itemView) {
            super(itemView);
            textDescTitle = itemView.findViewById(R.id.textDesMunic);
            textDesc= itemView.findViewById(R.id.textColo);
            textDescCalle = itemView.findViewById(R.id.textDesCalle);
            textDescColin = itemView.findViewById(R.id.textDesColin);
            textDescArea = itemView.findViewById(R.id.textDesArea);
            textDescPerim = itemView.findViewById(R.id.textDesPerim);
            textDescLatit = itemView.findViewById(R.id.textDesLatit);
            textDescLong = itemView.findViewById(R.id.textDesLong);
            textDescAreaR = itemView.findViewById(R.id.textDesAreaR);
            textDescHist = itemView.findViewById(R.id.textDesHist);
        }

        void setDescData(Description des){
            textDescTitle.setText(des.getDescTitle());
            textDesc.setText(des.getDesc());
            textDescCalle.setText(des.getDescCalle());
            textDescColin.setText(des.getDesColin());
            textDescArea.setText(des.getDesArea());
            textDescPerim.setText(des.getDesPerim());
            textDescLatit.setText(des.getDesLatit());
            textDescLong.setText(des.getDesLong());
            textDescAreaR.setText(des.getDesAreaR());
            textDescHist.setText(des.getDesHist());
        }
    }
}
