package com.example.githubclient;

import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

@Service
public class GithubClient {

    private String accessToken;
    static final String API_BASE_URL = "https://api.github.com/";
    static final String API_VERSION_SPEC = "application/vnd.github.v3+json";
    static final String JSON_CONTENT_TYPE = "application/json";

    private GithubApiInterface service;

    public GithubClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GithubApiInterface.class);
        this.accessToken = "token " + "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";
    }

    public List<Repository> getRepositories() throws IOException {
        Call<List<Repository>> retrofitCall = service.listRepos(accessToken, API_VERSION_SPEC);

        Response<List<Repository>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }

    public List<PullRequest> getPullRequses(String owner, String repoName) throws IOException {
        Call<List<PullRequest>> retrofitCall = service.listPuls(accessToken, API_VERSION_SPEC, repoName, owner);

        Response<List<PullRequest>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }

    public Repository createRepository(Repository repo) throws IOException {
        Call<Repository> retrofitCall = service.createRepo(repo, accessToken, API_VERSION_SPEC, JSON_CONTENT_TYPE);

        Response<Repository> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }
/*
    public DeletePayload deleteRepository(String owner, String repoName) throws IOException {
        Call<DeletePayload> retrofitCall = service.deleteRepo(accessToken, API_VERSION_SPEC, repoName, owner);

        Response<DeletePayload> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }
*/
    public List<PullInfo> getPullRequset(String owner, String repoName, Integer pullName) throws IOException {
        Call<List<PullInfo>> retrofitCall = service.listPullCommits(accessToken, API_VERSION_SPEC, repoName, owner, pullName);

        Response<List<PullInfo>> response = retrofitCall.execute();


        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }
}