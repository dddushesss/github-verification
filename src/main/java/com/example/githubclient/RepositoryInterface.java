package com.example.githubclient;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.event.DeletePayload;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RepositoryInterface {
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

    @DELETE("repos/{owner}/{repo}")
    Call<DeletePayload> deleteRepo(@Header("Authorization") String accessToken, @Header("Accept") String apiVersionSpec,
                                   @Path("repo") String repo, @Path("owner") String owner);


    @POST("user/repos")
    Call<Repository> createRepo(@Body Repository repo, @Header("Authorization") String accessToken,
                                @Header("Accept") String apiVersionSpec,
                                @Header("Content-Type") String contentType);
}
