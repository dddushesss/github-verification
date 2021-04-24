package com.example.githubclient;


import com.example.githubclient.Model.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class GitHubClientController {
    private final GithubClient githubService;

    public GitHubClientController(GithubClient githubService) {
        this.githubService = githubService;
    }


    @GetMapping("/repos")
    public List<Repository> getRepos() throws IOException {
        return githubService.getRepositories();
    }

    @GetMapping("/pulls/{owner}/{repo}")
    public List<PullRequest> getPuls(@PathVariable("owner") String owner,
                                     @PathVariable("repo") String repoName) throws IOException {
        return githubService.getPullRequests(owner, repoName);
    }

    @GetMapping("/pull/{owner}/{repo}/{pullNumber}")
    public List<PullInfo> getComits(@PathVariable("owner") String owner,
                                    @PathVariable("repo") String repoName,
                                    @PathVariable("pullNumber") Integer pullNumber) throws IOException {
        return githubService.getPullRequests(owner, repoName, pullNumber);
    }

    @GetMapping("/{owner}/{repo}/{issue_number}/issues")
    public List<Issue> getIssues(@PathVariable("owner") String owner,
                                 @PathVariable("repo") String repoName,
                                 @PathVariable("issue_number") Integer pullNum
                                   ) throws IOException {
        return githubService.getRepoIssues(owner, repoName, pullNum);
    }

    @GetMapping("/pull/{owner}/{repo}/{pull_number}/comments")
    public List<ReviewComment> getRevComs(@PathVariable("owner") String owner,
                                          @PathVariable("repo") String repo,
                                          @PathVariable("pull_number") Integer pullNum) throws IOException{
        return githubService.getReview(owner, repo, pullNum);
    }

    @PostMapping("/repos")
    public Repository createRepo(@RequestBody Repository newRepo) throws IOException {
        return githubService.createRepository(newRepo);
    }

    @PostMapping("/pull/{owner}/{repo}/pulls/{pull_number}/comments")
    public ReviewComment createRevComm(@RequestBody ReviewComment newRevComm,
                                       @PathVariable("owner") String owner,
                                       @PathVariable("repo") String repo,
                                       @PathVariable("pull_number") Integer pullNum) throws IOException{
        return githubService.createRevComm(newRevComm, owner, repo, pullNum);
    }

    @PostMapping("/pull/{owner}/{repo}/pulls/{pull_number}/issuecomments")
    public Issue createIssueComment(@RequestBody Issue newIssueComm,
                                       @PathVariable("owner") String owner,
                                       @PathVariable("repo") String repo,
                                       @PathVariable("pull_number") Integer pullNum) throws IOException{
        return githubService.createIssueComm(newIssueComm, owner, repo, pullNum);
    }
}
