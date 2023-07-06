package com.example.foodieapp;

public class Post {
    private String title;
    private String postId;
    private String description;


    public Post() {
        // Default constructor required for Firebase
    }
    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
