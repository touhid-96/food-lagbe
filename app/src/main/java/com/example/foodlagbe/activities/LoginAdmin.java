package com.example.foodlagbe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodlagbe.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class LoginAdmin extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        initComponent();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(LoginAdmin.this, AdminPanel.class);
            startActivity(intent);
            finish();
        }
    }

    private void initComponent() {
        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.email_admin);
        editTextPassword = findViewById(R.id.password_admin);
        buttonLogin = findViewById(R.id.btn_login_admin);
        buttonLogin.setOnClickListener(v -> {
            signIn();
        });
    }

    private void signIn() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (email.isEmpty()) {
            makeShortToast("Email required");
        } else if (password.isEmpty()) {
            makeShortToast("Password required");
        } else if (!email.equals("admin@mail.com")) {
            makeShortToast("Wrong credential");
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    makeShortToast("Login successful");

                    Intent intent=new Intent(this, AdminPanel.class);
                    startActivity(intent);
                    finish();
                } else {
                    makeLongToast(Objects.requireNonNull(task.getException()).getMessage());
                }
            });
        }
    }

    private void makeShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void makeLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}