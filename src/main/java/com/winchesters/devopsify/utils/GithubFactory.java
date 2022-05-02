package com.winchesters.devopsify.utils;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

public class GithubFactory {
    private static GitHub gitHub;
    public static GitHub getGitHub(String personalAccessToken) throws IOException {
        if (gitHub == null)
            gitHub =  new GitHubBuilder().withOAuthToken(personalAccessToken).build();
        return gitHub;
    }
    public static GitHub getGitHub() throws IOException {
        if (gitHub == null)
            gitHub =  new GitHubBuilder().withOAuthToken("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").build();
        return gitHub;
    }
}
