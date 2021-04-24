package com.example.githubclient.Model;

import com.google.gson.annotations.SerializedName;

public class ReviewComment {
    @SerializedName("body")
    public String body;

    public ReviewComment(String body) {
        this.body = body;
    }

    public ReviewComment() {
    }
}
