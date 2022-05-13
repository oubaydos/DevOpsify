package com.winchesters.devopsify.service.github;

import com.winchesters.devopsify.exception.github.PersonalAccessTokenPermissionException;
import com.winchesters.devopsify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    private String personalAccessToken;

    public GitHub getGithub() throws IOException {
        if (github == null) {
            personalAccessToken = userService.getPersonalAccessToken();
            github = connectToGithub(personalAccessToken);
        }
        return github;
    }

    @Override
    public GitHub connectToGithub(@NotNull @NotEmpty String personalAccessToken) throws IOException {
        if (github != null) return github;
        GitHub tempGithub = new GitHubBuilder().withOAuthToken(personalAccessToken).build();
        if (!verifyAllPermissionsGranted(tempGithub)) {
            throw new PersonalAccessTokenPermissionException();
        }
        userService.updatePersonalAccessToken(personalAccessToken);
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
