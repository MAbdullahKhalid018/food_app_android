package com.example.foodieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostingActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descriptionEditText;
    private Button postButton;
    private RecyclerView postRecyclerView;
    private DatabaseReference postsRef;
    private List<Post> postList;
    private PostAdapter postAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting);


        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        postButton = findViewById(R.id.postButton);
        postRecyclerView = findViewById(R.id.postRecyclerView);

        postsRef = FirebaseDatabase.getInstance().getReference("posts");
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(postList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        postRecyclerView.setLayoutManager(layoutManager);
        postRecyclerView.setAdapter(postAdapter);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();

                if (title.isEmpty() || description.isEmpty()) {
                    Toast.makeText(PostingActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    titleEditText.setError("Empty");
                    descriptionEditText.setError("Empty");
                } else

         {
                // Create a new post object
                Post post = new Post(title, description);

                // Generate a unique ID for the post
                String postId = postsRef.push().getKey();
                post.setPostId(postId);

                // Save the post to Firebase Realtime Database
                postsRef.child(postId).setValue(post);

                // Clear input fields
                titleEditText.getText().clear();
                descriptionEditText.getText().clear();
            }
        }
        });

        // Listen for changes in the posts collection
        postsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Post post = postSnapshot.getValue(Post.class);
                    postList.add(post);
                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
            }
        });
    }
}