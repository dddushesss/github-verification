package com.example.githubclient;


import com.example.githubclient.Model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface GithubApiInterface {
    @GET("user/repos")
    Call<List<Repository>> listRepos(@Header("Authorization") String accessToken,
                                     @Header("Accept") String apiVersionSpec);

    @GET("/repos/{owner}/{repo}/pulls/{pull_number}/commits")
    Call<List<PullInfo>> listPullCommits(@Header("Authorization") String accessToken,
                                         @Header("Accept") String apiVersionSpec,
                                         @Path("repo") String repo,
                                         @Path("owner") String owner,
                                         @Path("pull_number") Integer pullNumber);

    @GET("/repos/{owner}/{repo}/pulls")
    Call<List<PullRequest>> listPulls(@Header("Authorization") String accessToken,
                                      @Header("Accept") String apiVersionSpec,
                                      @Path("repo") String repo, @Path("owner") String owner);

    @GET("/repos/{owner}/{repo}/issues/comments")
    Call<List<Issue>> listIssues(@Header("Authorization") String accessToken,
                                 @Header("Accept") String apiVersionSpec,
                                 @Path("repo") String repo,
                                 @Path("owner") String owner);

    @GET("/repos/{owner}/{repo}/pulls/{pull_number}/reviews")
    Call<List<ReviewComment>> listRevCom(@Header("Authorization") String accessToken,
                                         @Header("Accept") String apiVersionSpec,
                                         @Path("repo") String repo,
                                         @Path("owner") String owner,
                                         @Path("pull_number") Integer pullNum);

    @POST("user/repos")
    Call<Repository> createRepo(@Body Repository repo, @Header("Authorization") String accessToken,
                                @Header("Accept") String apiVersionSpec,
                                @Header("Content-Type") String contentType);
}
