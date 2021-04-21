package com.example.githubclient;


import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface GithubApiInterface {
    @GET("user/repos")
    Call<List<Repository>> listRepos(@Header("Authorization") String accessToken,
                                     @Header("Accept") String apiVersionSpec);

    //repos/dddushesss/Geekrains_cs/pulls/1/commits
    @GET("/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    Call<List<PullInfo>> listPullCommits(@Header("Authorization") String accessToken,
                                         @Header("Accept") String apiVersionSpec,
                                         @Path("repo") String repo,
                                         @Path("owner") String owner,
                                         @Path("pull_number") Integer pullNumber);

    @GET("/repos/{owner}/{repo}/pulls")
    Call<List<PullRequest>> listPuls(@Header("Authorization") String accessToken,
                                     @Header("Accept") String apiVersionSpec,
                                     @Path("repo") String repo, @Path("owner") String owner);



    @POST("user/repos")
    Call<Repository> createRepo(@Body Repository repo, @Header("Authorization") String accessToken,
                                @Header("Accept") String apiVersionSpec,
                                @Header("Content-Type") String contentType);
}
