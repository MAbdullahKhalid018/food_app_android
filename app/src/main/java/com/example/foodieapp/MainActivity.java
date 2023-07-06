package com.example.foodieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodieapp.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    Button posting;

    ImageView img1, img2, img3;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posting=findViewById(R.id.posting);
        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);

        mAuth = FirebaseAuth.getInstance();
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Breakfast.class);
                startActivity(i);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, Lunch.class);
                startActivity(i);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this, Dinner.class);
                startActivity(in);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.login)
                {
                    Intent i=new Intent(MainActivity.this, Login_page.class);
                    startActivity(i);
                }
                else if(item.getItemId()==R.id.logout)
                {
                    signOutUser();
                }
                else if(item.getItemId()==R.id.help)
                {
                    Intent i=new Intent(MainActivity.this, Help_page.class);
                    startActivity(i);
                }
                return true;
            }
        });

        posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser==null){
                    startActivity(new Intent(MainActivity.this, Login_page.class));
                }
                else{
                    Intent i=new Intent(MainActivity.this, PostingActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private void signOutUser() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Signed out successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "No user is currently logged in.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToPostingActivity(View view)
    {
        startActivity(new Intent(MainActivity.this, PostingActivity.class));
    }
}