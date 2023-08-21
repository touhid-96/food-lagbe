package com.example.foodlagbe.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class AddItem extends AppCompatActivity {

    private Spinner categorySpinner;
    private EditText title_ET, nameET, priceET, ratingET;
    private Button saveBtn;
    private DatabaseReference spinnerDatabaseReference;
    private ArrayList<String> spinnerList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        initComponent();

        fetchCategories();
    }

    private void initComponent() {
        spinnerDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Category");

        spinnerList = new ArrayList<>();
        adapter = new ArrayAdapter<>(AddItem.this, android.R.layout.simple_spinner_dropdown_item, spinnerList);

        categorySpinner = findViewById(R.id.category_spinner);
        title_ET = findViewById(R.id.title_et);
        nameET = findViewById(R.id.name_et);
        priceET = findViewById(R.id.price_et);
        ratingET = findViewById(R.id.rating_et);
        saveBtn = findViewById(R.id.save_button);
        saveBtn.setOnClickListener(v -> {
            saveData();
        });
    }

    /**
     * Testing purpose
     */
    private void setCategoryDropDownSpinnerManually() {
        spinnerList.clear();

        spinnerList.add("Domain & Hosting");
        spinnerList.add("Graphics Design");
        spinnerList.add("SEO Service");
        spinnerList.add("Social Media Marketing");
        spinnerList.add("Software Development");

        adapter.notifyDataSetChanged();
        categorySpinner.setAdapter(adapter);
    }

    private void fetchCategories() {
        spinnerDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinnerList.clear();
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    spinnerList.add(categorySnapshot.getKey());
                }
                adapter.notifyDataSetChanged();
                categorySpinner.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                makeLongToast(error.getMessage());
            }
        });
    }

    private void saveData() {
        String selectedCategory = categorySpinner.getSelectedItem().toString();
        String title = title_ET.getText().toString();
        String name = nameET.getText().toString();
        String price = priceET.getText().toString();
        String rating = ratingET.getText().toString();

        DatabaseReference categoryReference = spinnerDatabaseReference.child(selectedCategory);
        DatabaseReference itemReference = categoryReference.child(title);

        itemReference.child("name").setValue(name);
        itemReference.child("price").setValue(price);
        itemReference.child("rating").setValue(rating);

        clearFields();
        makeShortToast("Data saved successfully!");
    }

    private void clearFields() {
        title_ET.setText("");
        nameET.setText("");
        priceET.setText("");
        ratingET.setText("");
    }

    private void makeLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void makeShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}