package com.example.foodlagbe.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foodlagbe.R;


public class DailyMealFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.daily_meal_fragment,container,false);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}