package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_page extends AppCompatActivity {


    Button loginButton;
    private FirebaseAuth mAuth;
    TextView createAcc;
    TextView forgetPassword;
    TextInputEditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        createAcc = findViewById(R.id.createAcc);
        forgetPassword = findViewById(R.id.forgetPassword);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_page.this, Signup_page.class);
                startActivity(i);
            }
        });
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString().trim();
                String password2 = password.getText().toString().trim();
                loginUser(email1, password2);
                email.getText().clear();
                password.getText().clear();
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleForgotPassword();
            }
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String username = user.getEmail();
                            updateAppTitle(username);
                        }
                        Intent intent = new Intent(Login_page.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        // Login failed, display an error message
                        Toast.makeText(Login_page.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void handleForgotPassword() {
        // Get the email address entered by the user
        String uu_email = email.getText().toString().trim();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (!uu_email.isEmpty()){
            mAuth.sendPasswordResetEmail(uu_email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Password reset email sent successfully
                        Toast.makeText(Login_page.this, "Password reset email sent.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Password reset email sending failed
                        Toast.makeText(Login_page.this, "Failed to send password reset email.", Toast.LENGTH_SHORT).show();
                    }
                });
            email.getText().clear();
        }
        else {
            Toast.makeText(this, "Email Field cannot be empty!", Toast.LENGTH_SHORT).show();
            email.setError("Empty");
        }
    }
    private void updateAppTitle(String username)
    {
        String appName = getString(R.string.app_name);
        String newTitle = username;
        setTitle(newTitle);
    }
}