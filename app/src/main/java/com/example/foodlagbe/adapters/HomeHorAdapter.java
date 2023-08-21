package com.example.foodlagbe.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodlagbe.R;
import com.example.foodlagbe.models.HomeHorModel;
import com.example.foodlagbe.models.HomeVermodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {
    DatabaseReference databaseReference;
    UpdateverticalRec updateverticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;
    boolean check =true;
    boolean select =true;
    int row_index=-1;

    public HomeHorAdapter(UpdateverticalRec updateverticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateverticalRec = updateverticalRec;
        this.activity = activity;
        this.list = list;
    }


    @NonNull
    @Override
    public HomeHorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeHorAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horitzontal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHorAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        if(check) {
            databaseReference = FirebaseDatabase.getInstance().getReference("Category").child("Web Design");
            ArrayList<HomeVermodel> homeVermodels = new ArrayList<>();
            homeVermodels.add(new HomeVermodel(R.drawable.daraz,"Daraz Type","https://e-commerce1.techgiant.pro/","4.8","$500"));
            homeVermodels.add(new HomeVermodel(R.drawable.bagdoom,"Bagdoom Type","http://e-commerce2.techgiant.pro/","4.7","$350"));
            homeVermodels.add(new HomeVermodel(R.drawable.fresh_mart, "Premium Type", "http://e-commerce4.techgiant.pro/", "4.9", "$500"));
            homeVermodels.add(new HomeVermodel(R.drawable.accessories, "Customize Type", "http://e-commerce3.techgiant.pro/", "4.2", "$480"));
            homeVermodels.add(new HomeVermodel(R.drawable.pizza11,"Special Pizza","10:00am - 10:00pm","4.9","৳25"));

            /*databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    System.out.println("-------------------------------------------------------------------------------------------" + snapshot.getChildrenCount());
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        HomeVermodel homeVermodel = dataSnapshot.getValue(HomeVermodel.class);
                        homeVermodels.add(homeVermodel);
                    }
                    //homeVarAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/

            updateverticalRec.callBack(position, homeVermodels);
            check = false;
        }
        holder.cardview.setOnClickListener(view -> {
            row_index =position;
            notifyDataSetChanged();

            if(position==0){
                databaseReference = FirebaseDatabase.getInstance().getReference("Category").child("Web Design");
                ArrayList<HomeVermodel>homeVermodels=new ArrayList<>();
                homeVermodels.add(new HomeVermodel(R.drawable.daraz,"Daraz Type","https://e-commerce1.techgiant.pro/","4.9","$500"));
                homeVermodels.add(new HomeVermodel(R.drawable.bagdoom,"Bagdoom Type","http://e-commerce2.techgiant.pro/","4.9","$500"));
                homeVermodels.add(new HomeVermodel(R.drawable.fresh_mart, "Premium Type", "http://e-commerce4.techgiant.pro/", "4.9", "$500"));
                homeVermodels.add(new HomeVermodel(R.drawable.accessories, "Customize Type", "http://e-commerce3.techgiant.pro/", "4.9", "$500"));
                homeVermodels.add(new HomeVermodel(R.drawable.pizza11,"Special Pizza","10:00am - 10:00pm","4.9","৳25"));

                /*databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        System.out.println("-------------------------------------------------------------------------------------------" + snapshot.getChildrenCount());
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            HomeVermodel homeVermodel = dataSnapshot.getValue(HomeVermodel.class);
                            homeVermodels.add(homeVermodel);
                        }
                        //homeVarAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

                updateverticalRec.callBack(position,homeVermodels);
            } else if(position==1){
                ArrayList<HomeVermodel>homeVermodels=new ArrayList<>();
                homeVermodels.add(new HomeVermodel(R.drawable.newspaper,"Newspaper","https://www.news1.techgiant.pro/","4.5","$500"));
                homeVermodels.add(new HomeVermodel(R.drawable.school,"School","http://school1.techgiant.pro/","4.3","$500"));
                homeVermodels.add(new HomeVermodel(R.drawable.hospital,"Hospital","http://hospital1.techgiant.pro/","4.8","$500"));
                homeVermodels.add(new HomeVermodel(R.drawable.pos,"POS","http://pos1.techgiant.pro/dashboard","4.9","৳5005"));

                updateverticalRec.callBack(position,homeVermodels);

            } else if(position==2){
                ArrayList<HomeVermodel>homeVermodels=new ArrayList<>();
                homeVermodels.add(new HomeVermodel(R.drawable.seo_1,"Silver Package","https://www.techgiant.pro/price-plan/18","4.9","$80"));
                homeVermodels.add(new HomeVermodel(R.drawable.seo_2,"Gold Package","https://www.techgiant.pro/price-plan/20","4.9","$100"));
                /*homeVermodels.add(new HomeVermodel(R.drawable.fries2,"Chezze fries","10:00am - 10:00pm","4.9","৳25"));
                homeVermodels.add(new HomeVermodel(R.drawable.fries3," Ring fries","10:00am - 10:00pm","4.9","৳25"));*/


                updateverticalRec.callBack(position,homeVermodels);

            } else if(position==3){
                ArrayList<HomeVermodel>homeVermodels=new ArrayList<>();
                homeVermodels.add(new HomeVermodel(R.drawable.com,".Com","https://www.portal.shadhinhost.com/domain/pricing","4.9","$3"));
                homeVermodels.add(new HomeVermodel(R.drawable.other_hosting,"Others","https://www.portal.shadhinhost.com/domain/pricing","4.9","$1.5"));


                updateverticalRec.callBack(position,homeVermodels);

            } else if(position==4){
                ArrayList<HomeVermodel>homeVermodels=new ArrayList<>();
                homeVermodels.add(new HomeVermodel(R.drawable.fb,"Facebook Marketing","https://www.techgiant.pro/price-plan/18","5.0","$80"));
                homeVermodels.add(new HomeVermodel(R.drawable.twitter,"Twitter Marketing","https://www.techgiant.pro/price-plan/18","4.7","$80"));
                homeVermodels.add(new HomeVermodel(R.drawable.insta,"Instagram Marketing","https://www.techgiant.pro/price-plan/18","4.9","$80"));
                /*homeVermodels.add(new HomeVermodel(R.drawable.steak4," Tomahawk","10:00am - 10:00pm","4.9","৳25"));*/

                updateverticalRec.callBack(position,homeVermodels);

            } else if(position==5){
                ArrayList<HomeVermodel>homeVermodels=new ArrayList<>();
                homeVermodels.add(new HomeVermodel(R.drawable.logo_design,"Logo design","https://www.techgiant.pro/price-plan/22","4.9","$3"));
                homeVermodels.add(new HomeVermodel(R.drawable.business_card,"Business Card Design","https://www.techgiant.pro/price-plan/24","4.9","$3"));
                homeVermodels.add(new HomeVermodel(R.drawable.bruiser,"Bruiser Design","https://www.techgiant.pro/price-plan/26","4.9","$3"));
                homeVermodels.add(new HomeVermodel(R.drawable.book_cover,"Book Cover Design","https://www.techgiant.pro/price-plan/28","4.9","$3"));


                updateverticalRec.callBack(position,homeVermodels);

            }
            /*else if(position==6){
                ArrayList<HomeVermodel>homeVermodels=new ArrayList<>();
                homeVermodels.add(new HomeVermodel(R.drawable.ice1," Mixed Ice Cream","10:00am - 10:00pm","4.9","৳25"));
                homeVermodels.add(new HomeVermodel(R.drawable.ice2,"Vanila Ice Cream","10:00am - 10:00pm","4.9","৳25"));
                homeVermodels.add(new HomeVermodel(R.drawable.ice3," Chocolate Ice Creamr","10:00am - 10:00pm","4.9","৳25"));
                homeVermodels.add(new HomeVermodel(R.drawable.ice4," strawberry ice cream","10:00am - 10:00pm","4.9","৳25"));

                updateverticalRec.callBack(position,homeVermodels);

            }*/
        });
        if(select){
            if(position==0){
                holder.cardview.setBackgroundResource(R.drawable.change_bg);
                select=false;
            }
        }else {
            if(row_index==position){
                holder.cardview.setBackgroundResource(R.drawable.change_bg);
            }else {
                holder.cardview.setBackgroundResource(R.drawable.hor_desig);
            }
        }
    }




    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;

        CardView cardview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.hor_img);
            name=itemView.findViewById(R.id.hor_text);
            cardview=itemView.findViewById(R.id.cardview);

        }
    }

}