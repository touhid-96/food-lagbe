package com.example.foodlagbe.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.foodlagbe.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class EditItem extends AppCompatActivity {

    private Spinner categorySpinner, titleSpinner;
    private EditText nameET, priceET, ratingET;
    private Button updateBtn;
    private DatabaseReference categoryDatabaseReference, titleDatabaseReference, itemDatabaseReference;
    private ArrayList<String> categorySpinnerList, titleSpinnerList;
    private ArrayAdapter<String> categoryAdapter, titleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        initComponent();

        fetchCategories();
    }

    private void initComponent() {
        categoryDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Category");

        categorySpinnerList = new ArrayList<>();
        categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categorySpinnerList);

        titleSpinnerList = new ArrayList<>();
        titleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, titleSpinnerList);

        categorySpinner = findViewById(R.id.category_spinner);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String selectedCategory = categorySpinnerList.get(position);
                fetchTitles();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        titleSpinner = findViewById(R.id.title_spinner);
        titleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String selectedTitle = titleSpinnerList.get(position);
                fetchItemData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        nameET = findViewById(R.id.name_et);
        priceET = findViewById(R.id.price_et);
        ratingET = findViewById(R.id.rating_et);
        updateBtn = findViewById(R.id.update_button);
        updateBtn.setOnClickListener(v -> {
            updateData();
        });
    }

    private void fetchItemData() {
        itemDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Category")
                .child(categorySpinner.getSelectedItem().toString())
                .child(titleSpinner.getSelectedItem().toString());

        itemDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    nameET.setText(Objects.requireNonNull(snapshot.child("name").getValue()).toString());
                    priceET.setText(Objects.requireNonNull(snapshot.child("price").getValue()).toString());
                    ratingET.setText(Objects.requireNonNull(snapshot.child("rating").getValue()).toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                makeLongToast(error.getMessage());
            }
        });
    }

    private void fetchTitles() {
        titleDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Category")
                .child(categorySpinner.getSelectedItem().toString());

        titleDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                titleSpinnerList.clear();
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    titleSpinnerList.add(categorySnapshot.getKey());
                }
                titleAdapter.notifyDataSetChanged();
                titleSpinner.setAdapter(titleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                makeLongToast(error.getMessage());
            }
        });
    }

    private void fetchCategories() {
        categoryDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categorySpinnerList.clear();
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    categorySpinnerList.add(categorySnapshot.getKey());
                }
                categoryAdapter.notifyDataSetChanged();
                categorySpinner.setAdapter(categoryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                makeLongToast(error.getMessage());
            }
        });
    }

    private void updateData() {
        String selectedCategory = categorySpinner.getSelectedItem().toString();
        String selectedTitle = titleSpinner.getSelectedItem().toString();
        String name = nameET.getText().toString();
        String price = priceET.getText().toString();
        String rating = ratingET.getText().toString();

        DatabaseReference itemReference = FirebaseDatabase.getInstance().getReference()
                .child("Category")
                .child(selectedCategory)
                .child(selectedTitle);

        itemReference.child("name").setValue(name);
        itemReference.child("price").setValue(price);
        itemReference.child("rating").setValue(rating);

        makeLongToast("Data updated successfully!");
    }

    private void makeLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void makeShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}