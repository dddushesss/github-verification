package com.example.githubclient;

import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.event.DeletePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class GitHubClientController {
    @Autowired
    private GitHubService githubService;


    @GetMapping("/repos")
    public List<Repository> getRepos() throws IOException {
        return githubService.getRepositories();
    }

    @GetMapping("/puls/{owner}/{repo}")
    public List<PullRequest> getPuls(@PathVariable("owner") String owner,
                                     @PathVariable("repo") String repoName) throws IOException {
        return githubService.getPullRequses(owner, repoName);
    }

    @GetMapping("/pull/{owner}/{repo}/{pull_number}")
    public List<PullInfo> getComits(@PathVariable("owner") String owner,
                                    @PathVariable("repo") String repoName,
                                    @PathVariable("pull_number") Integer pullNumber) throws IOException {
        return githubService.getPullRequset(owner, repoName, pullNumber);
    }

    @PostMapping("/repos")
    public Repository createRepo(@RequestBody Repository newRepo) throws IOException {
        return githubService.createRepository(newRepo);
    }

    @DeleteMapping("/repos/{owner}/{repo}")
    public DeletePayload deleteRepo(
            @PathVariable("owner") String owner,
            @PathVariable("repo") String repoName) throws IOException {
        return githubService.deleteRepository(owner, repoName);
    }
}
