package com.example.githubclient;

import com.example.githubclient.Model.Repository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

public class GithubServiceTest extends AbstractTest {


    GitHubService service;
    GithubClient mock;

    @Before
    public void init() throws IOException {
        mock = Mockito.mock(GithubClient.class, RETURNS_DEEP_STUBS);
        service = new GitHubService(mock);
    }

    @Test
    public void testGetRepos() throws IOException{

        Repository repository = new Repository();
        repository.setGitURL("Test Repo");
        when(mock.getRepositories()).thenReturn(Collections.singletonList(repository));
        List<Repository> repositories = service.getRepos();
        assertEquals("Hello Test Repo", repositories.get(0).url);
    }

}
