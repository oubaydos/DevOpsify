package com.winchesters.devopsify.service;

import com.winchesters.devopsify.exception.PersonalAccessTokenPermissionException;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class GithubServiceImplementation implements  GithubService{

    private static final Logger LOG = LoggerFactory.getLogger(GithubServiceImplementation.class);
    private GitHub github;

   @Override
    public void initGit(String personalAccessToken) throws IOException {
        github = new GitHubBuilder().withOAuthToken(personalAccessToken).build();
        if (!verifyAllPermissionsGranted()) {
            throw new PersonalAccessTokenPermissionException();
        }
    }

    private boolean verifyAllPermissionsGranted() throws IOException {
        if (github != null) {
            if (github.getMyself() != null) {
                if (github.getMyself().getResponseHeaderFields() != null) {
                    if (github.getMyself().getResponseHeaderFields().get("x-oauth-scopes") != null) {
                        List<String> temp = Arrays.asList(github.getMyself().getResponseHeaderFields().get("x-oauth-scopes").get(0).split("\\s*,\\s*"));
                        LOG.debug("x-oauth-scopes : {}", temp);
                        return temp.containsAll(allPermissions);
                    }
                }
            }
        }
        return false;
    }
}