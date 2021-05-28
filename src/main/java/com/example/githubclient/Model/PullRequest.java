package com.example.githubclient.Model;

import com.google.gson.annotations.SerializedName;

enum PullRequestState{

}

public class PullRequest {

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @SerializedName("user")
    public User user;
    @SerializedName("body")
    public String body;
    @SerializedName("title")
    public String title;
    @SerializedName("state")
    public String state;

}
