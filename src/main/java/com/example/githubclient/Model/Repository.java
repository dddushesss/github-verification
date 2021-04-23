package com.example.githubclient.Model;

import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("owner")
    public User user;
    @SerializedName("description")
    public String description;
}
