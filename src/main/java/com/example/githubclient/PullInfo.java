package com.example.githubclient;

import com.google.gson.annotations.SerializedName;

class Commit {
    @SerializedName("message")
    public String message;

}

public class PullInfo {

    @SerializedName("commit")
    public Commit commit;

}
