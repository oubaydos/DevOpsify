package com.winchesters.devopsify.service.github;

import com.winchesters.devopsify.dto.GithubRepositoryDto;
import lombok.NonNull;
import org.kohsuke.github.GHRepository;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public interface GithubRepository {
    /**
     *  create a new repository in github
     * @param githubRepositoryDto an object containing name, owner, autoInit, licenseTemplate, gitIgnoreTemplate attributes
     * @throws IOException : connection problems
     * @return the created repository
     */
    GHRepository createRepository(GithubRepositoryDto githubRepositoryDto
    ) throws IOException;
}
