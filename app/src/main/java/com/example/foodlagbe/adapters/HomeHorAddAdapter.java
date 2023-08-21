package com.example.foodlagbe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodlagbe.R;
import com.example.foodlagbe.models.HomeHorAddModel;


import java.util.List;

public class HomeHorAddAdapter extends RecyclerView.Adapter<HomeHorAddAdapter.ViewHolder> {
    Context context;
    List<HomeHorAddModel> list;

    public HomeHorAddAdapter(Context context, List<HomeHorAddModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeHorAddAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_hor_add,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHorAddAdapter.ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.hor_imag);

        }
    }
}
