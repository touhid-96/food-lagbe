package com.example.foodlagbe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.foodlagbe.MainActivity;
import com.example.foodlagbe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){

            if(currentUser.getEmail().equals("admin@mail.com")) {
                Intent intent=new Intent(Welcome.this, AdminPanel.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent=new Intent(Welcome.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            /*Intent intent=new Intent(Welcome.this, MainActivity.class);
            startActivity(intent);
            finish();*/
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        mAuth=FirebaseAuth.getInstance();
    }

    public void register(View view) {
        startActivity(new Intent(Welcome.this, Register.class));
    }

    public void login(View view) {
        startActivity(new Intent(Welcome.this, Login.class));
    }
    public void loginAdmin(View view) {
        startActivity(new Intent(Welcome.this, LoginAdmin.class));
    }
}