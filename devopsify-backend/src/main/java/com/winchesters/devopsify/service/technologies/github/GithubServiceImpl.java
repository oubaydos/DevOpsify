package com.winchesters.devopsify.service.technologies.github;

import com.winchesters.devopsify.exception.UserCredentialsNotFoundException;
import com.winchesters.devopsify.exception.github.PersonalAccessTokenPermissionException;
import com.winchesters.devopsify.model.GithubCredentials;
import com.winchesters.devopsify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Validated
@Service
@Transactional
@RequiredArgsConstructor
public class GithubServiceImpl implements GithubService {

    private static final Logger LOG = LoggerFactory.getLogger(GithubServiceImpl.class);
    private GitHub github;
    private final UserService userService;
    private GithubCredentials githubCredentials;

    public GitHub getGithub() throws IOException {
        if (github == null) {
            githubCredentials = userService.getGithubCredentials();
            github = connectToGithub(githubCredentials);
        }
        return github;
    }

    @Override
    public GitHub connectToGithub(@NotNull GithubCredentials githubCredentials) throws IOException {
        if (github != null) return github;
        if (githubCredentials == null || githubCredentials.personalAccessToken() == null || githubCredentials.username() == null)
            throw new UserCredentialsNotFoundException();
        GitHub tempGithub = new GitHubBuilder().withOAuthToken(githubCredentials.personalAccessToken()).build();
        if (!verifyAllPermissionsGranted(tempGithub)) {
            throw new PersonalAccessTokenPermissionException();
        }
        this.githubCredentials = githubCredentials;
        this.github = tempGithub;
        userService.updateGithubCredentials(githubCredentials);
        return tempGithub;
    }

    @SuppressWarnings("deprecation")
    private boolean verifyAllPermissionsGranted(@NotNull GitHub myGitHub) throws IOException {
        if (myGitHub != null &&
                myGitHub.getMyself() != null &&
                myGitHub.getMyself().getResponseHeaderFields() != null &&
                myGitHub.getMyself().getResponseHeaderFields().get("x-oauth-scopes") != null
        ) {
            List<String> temp = Arrays.asList(
                    myGitHub.getMyself().getResponseHeaderFields()
                            .get("x-oauth-scopes")
                            .get(0)
                            .split("\\s*,\\s*")
            );
            LOG.debug("x-oauth-scopes : {}", temp);
            return temp.containsAll(allPermissions);
        }

        return false;
    }


}
