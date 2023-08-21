package com.example.foodlagbe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.foodlagbe.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        initComponent();
    }

    private void initComponent() {
        Button addItem = findViewById(R.id.add_item_btn);
        Button editItem = findViewById(R.id.edit_item_btn);
        Button deleteItem = findViewById(R.id.delete_item_btn);
        Button logoutBtn = findViewById(R.id.logout_btn);

        addItem.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddItem.class);
            startActivity(intent);
        });
        editItem.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditItem.class);
            startActivity(intent);
        });
        deleteItem.setOnClickListener(v -> {
            Intent intent = new Intent(this, DeleteItem.class);
            startActivity(intent);
        });
        logoutBtn.setOnClickListener(v -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();

            Intent intent = new Intent(this, Welcome.class);
            startActivity(intent);
            finish();
        });
    }
}