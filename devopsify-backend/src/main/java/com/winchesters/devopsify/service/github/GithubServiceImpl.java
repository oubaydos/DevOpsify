package com.winchesters.devopsify.service.github;

import com.winchesters.devopsify.exception.PersonalAccessTokenPermissionException;
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

import javax.annotation.PostConstruct;
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
    protected GitHub github;
    private final UserService userService;

    @Autowired
    ApplicationContext context;


    @PostConstruct
    public void initGithub() throws IOException {
        if (userService.getCurrentUser() != null && userService.getCurrentUser().getPersonalAccessToken() != null && !userService.getCurrentUser().getPersonalAccessToken().isEmpty())
            connectToGithub(userService.getCurrentUser().getPersonalAccessToken());
    }

    @Override
    public GitHub connectToGithub(@NotNull @NotEmpty String personalAccessToken) throws IOException {
        LOG.debug("is empty ? {}", personalAccessToken.isEmpty());
        if (github != null) return github;
//        github = new GitHubBuilder().withOAuthToken(personalAccessToken).build();
        context.getBean(GithubServiceImpl.class).github = new GitHubBuilder().withOAuthToken(personalAccessToken).build();
        github = context.getBean(GithubServiceImpl.class).github;
        if (!verifyAllPermissionsGranted()) {
            throw new PersonalAccessTokenPermissionException();
        }
        userService.updatePersonalAccessToken(personalAccessToken);
        return github;
    }

    private boolean verifyAllPermissionsGranted() throws IOException {
        if (github != null &&
                github.getMyself() != null &&
                github.getMyself().getResponseHeaderFields() != null &&
                github.getMyself().getResponseHeaderFields().get("x-oauth-scopes") != null
        ) {
            List<String> temp = Arrays.asList(
                    github.getMyself().getResponseHeaderFields()
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
