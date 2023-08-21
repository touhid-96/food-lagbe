package com.example.foodlagbe.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodlagbe.R;

import com.example.foodlagbe.adapters.HomeHorAdapter;
import com.example.foodlagbe.adapters.HomeHorAddAdapter;
import com.example.foodlagbe.adapters.HomeVarAdapter;
import com.example.foodlagbe.adapters.UpdateverticalRec;
import com.example.foodlagbe.databinding.FragmentHomeBinding;
import com.example.foodlagbe.models.HomeHorAddModel;
import com.example.foodlagbe.models.HomeHorModel;
import com.example.foodlagbe.models.HomeVermodel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateverticalRec {

    DatabaseReference databaseReference;
    RecyclerView homeHorizontalRec, homehorizontalAddrec, homeVerticalRec;
    ArrayList<HomeHorModel>homeHorModelList;
    List<HomeHorAddModel>homeHorAddModels;
    ArrayList<HomeVermodel>homeVermodelList;
    HomeVarAdapter homeVarAdapter;
    HomeHorAddAdapter homeHorAddAdapter;
    HomeHorAdapter homeHorAdapter;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        databaseReference = FirebaseDatabase.getInstance().getReference("Category").child("Web Design");


        //////////horizontal model
        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        homeHorModelList =new ArrayList<>();
        //firebase database retrieve code needed here
        homeHorModelList.add(new HomeHorModel(R.drawable.web_design,"Web Design"));
        homeHorModelList.add(new HomeHorModel(R.drawable.soft_dev,"Software Development"));
        homeHorModelList.add(new HomeHorModel(R.drawable.seo,"SEO Service"));
        homeHorModelList.add(new HomeHorModel(R.drawable.hosting,"Domain & Hosting"));
        homeHorModelList.add(new HomeHorModel(R.drawable.social,"Social Media Marketing"));
        homeHorModelList.add(new HomeHorModel(R.drawable.graphics_design,"Graphics Design"));

        homeHorAdapter =new HomeHorAdapter(this,getActivity(),homeHorModelList);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);


        //////////horizontal add
        homehorizontalAddrec = root.findViewById(R.id.home_hor_add);
        homeHorAddModels =new ArrayList<>();
        homeHorAddModels.add(new HomeHorAddModel(R.drawable.offer_1,""));
        homeHorAddModels.add(new HomeHorAddModel(R.drawable.offer_2,""));
        homeHorAddModels.add(new HomeHorAddModel(R.drawable.offer_3,""));
        homeHorAddAdapter =new HomeHorAddAdapter(getActivity(),homeHorAddModels);
        homehorizontalAddrec.setAdapter(homeHorAddAdapter);
        homehorizontalAddrec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        homehorizontalAddrec.setHasFixedSize(true);
        homehorizontalAddrec.setNestedScrollingEnabled(false);


        //////////vertical model
        homeVerticalRec = root.findViewById(R.id.home_ver_rec); //recyclerView
        homeVermodelList = new ArrayList<>();
        homeVarAdapter = new HomeVarAdapter(getActivity(),homeVermodelList);
        homeVerticalRec.setAdapter(homeVarAdapter);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void callBack(int position, ArrayList<HomeVermodel> list) {
        homeVarAdapter=new HomeVarAdapter(getContext(),list);
        homeVarAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVarAdapter);
    }
}