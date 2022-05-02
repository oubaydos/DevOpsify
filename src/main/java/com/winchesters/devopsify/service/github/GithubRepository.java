package com.winchesters.devopsify.service.github;

import com.sun.istack.NotNull;
import lombok.NonNull;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

public interface GithubRepository {
    /**
     *  create a new repository in github
     * @param personalAccessToken personal access token for GitHub account
     * @param name name of the repository
     * @param autoInit If true, create an initial commit with empty README
     * @param licenseTemplate Desired license template to apply
     * @param gitIgnoreTemplate Creates a default .gitignore
     * @param owner Specifies the ownership of the repository
     * @throws IOException : connection problems
     * @return
     */
    GHRepository createRepository(@NotNull String personalAccessToken,
                                  @NonNull String name,
                                  Boolean autoInit,
                                  String licenseTemplate,
                                  String gitIgnoreTemplate,
                                  String owner
    ) throws IOException;
}
