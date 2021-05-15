package com.example.githubclient;

import com.example.githubclient.Model.Commit;
import com.example.githubclient.Model.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {
    private final GithubClient client;

    public GitHubService(GithubClient client) {
        this.client = client;
    }

    public List<Repository> getRepos() throws IOException {
        return client.getRepositories().stream()
                .map(Repository::getGitUrl)
                .map(x -> "Hello " + x)
                .map(x -> {
                    Repository repository = new Repository();
                    repository.setGitURL(x);
                    return repository;
                }).collect(Collectors.toList());

    }
}
