package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_page extends AppCompatActivity {

    TextView logbt;
    Button signupButton;
    private FirebaseAuth mAuth;

    TextInputEditText un_name, u_email, u_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        mAuth = FirebaseAuth.getInstance();
        init();

        logbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Signup_page.this,Login_page.class);
                startActivity(i);
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=un_name.getText().toString().trim();
                String email=u_email.getText().toString().trim();
                String password=u_password.getText().toString().trim();
                signupUser(name, email, password);
            }
        });
    }
    public void init()
    {
        logbt=findViewById(R.id.logbt);
        signupButton=findViewById(R.id.signupButton);
        un_name=findViewById(R.id.un_name);
        u_email=findViewById(R.id.u_email);
        u_password=findViewById(R.id.u_password);
    }

    private void signupUser(String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Signup successful, navigate to the main activity
                        Intent intent = new Intent(Signup_page.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        // Signup failed, display an error message
                        Toast.makeText(Signup_page.this, "Signup failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}