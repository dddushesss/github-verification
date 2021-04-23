package com.example.githubclient.Model;


import com.google.gson.annotations.SerializedName;


public class Issue {
    @SerializedName("user")
    public User user;
    @SerializedName("body")
    public String body;
}
