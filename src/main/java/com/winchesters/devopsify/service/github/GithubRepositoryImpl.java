package com.winchesters.devopsify.service.github;

import com.sun.istack.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GithubRepositoryImpl implements GithubRepository {
    private final GithubServiceImpl githubServiceImpl;

    @Override
    public GHRepository createRepository(@NotNull String personalAccessToken,
                                         @NonNull String name,
                                         Boolean autoInit,
                                         String licenseTemplate,
                                         String gitIgnoreTemplate,
                                         String owner
    ) throws IOException {
        githubServiceImpl.connectToGithub(personalAccessToken);
        return githubServiceImpl.github.createRepository(name)
                .autoInit(Optional.ofNullable(autoInit).orElse(false))
                .licenseTemplate(licenseTemplate)
                .gitignoreTemplate(gitIgnoreTemplate)
                .owner(owner)
                .create();
    }
}
