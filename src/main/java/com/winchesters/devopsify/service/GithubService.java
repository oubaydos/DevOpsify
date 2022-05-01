package com.winchesters.devopsify.service;

import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public interface GithubService {

    /**
     * all permissions for personal access tokens
     */
    List<String> allPermissions = List.of("admin:enterprise", "admin:gpg_key", "admin:org", "admin:org_hook", "admin:public_key", "admin:repo_hook", "delete:packages", "delete_repo", "gist", "notifications", "repo", "user", "workflow", "write:discussion", "write:packages");

    /**
     * initialize github and verify access token
     * @param personalAccessToken personal access token : (https://github.com/settings/tokens)
     * @throws IOException if a problem occurs while connecting to github with the personalAccessToken
     */
    void initGit(String personalAccessToken) throws IOException;
}
