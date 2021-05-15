package com.example.githubclient;

import com.example.githubclient.Model.Repository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

public class GithubClientApplicationTests extends AbstractTest {

    @Autowired
    GitHubTestService service;
    GithubClient mock;

    @Before
    public void init() throws IOException {
        mock = Mockito.mock(GithubClient.class, RETURNS_DEEP_STUBS);
        service = new GitHubTestService(mock);
    }

    @Test
    public void testGetRepos() throws IOException{

        Repository repository = new Repository();
        repository.setGitURL("Test Repo");
        when(mock.getRepositories()).thenReturn(Arrays.asList(repository));
        List<Repository> repositories = service.getRepos();
        assertEquals("Hello Test Repo", repositories.get(0).url);
    }

}
