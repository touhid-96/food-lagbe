package com.example.foodlagbe.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodlagbe.R;
import com.example.foodlagbe.models.HomeVermodel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class HomeVarAdapter extends RecyclerView.Adapter<HomeVarAdapter.ViewHolder> {
    private BottomSheetDialog bottomSheetDialog;
    Context context;
    ArrayList<HomeVermodel> list;

    public HomeVarAdapter(Context context, ArrayList<HomeVermodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeVarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVarAdapter.ViewHolder holder, int position) {
        final String mName= list.get(position).getName();
        final String mTiming= list.get(position).getTiming();
        final String mRating= list.get(position).getRating();
        final String Mprice= list.get(position).getPrice();
        final int mImage=list.get(position).getImage();

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.name.setText(list.get(position).getName());
        holder.rating.setText(list.get(position).getRating());
        holder.price.setText(list.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog =new BottomSheetDialog(context,R.style.BottomSheetTheme);
                View sheetView=LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout,null);
                sheetView.findViewById(R.id.add_chart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context,"Added To Chart",Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });
                sheetView.findViewById(R.id.preview_button).setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("QueryPermissionsNeeded")
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(mTiming);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);
                        //bottomSheetDialog.dismiss();
                    }
                });
                ImageView bottomImg=sheetView.findViewById(R.id.bottom_img);
                TextView bottomName=sheetView.findViewById(R.id.bottom_name);
                TextView bottomPrice=sheetView.findViewById(R.id.bottom_price);
                TextView bottomRating=sheetView.findViewById(R.id.bottom_rating);

                bottomName.setText(mName);
                bottomPrice.setText(Mprice);
                bottomRating.setText(mRating);
                bottomImg.setImageResource(mImage);

                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,timing,rating,price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.ver_img);
            name=itemView.findViewById(R.id.name);
            timing=itemView.findViewById(R.id.timing);
            rating=itemView.findViewById(R.id.rating);
            price=itemView.findViewById(R.id.price);
        }
    }
}
