package com.winchesters.devopsify.service.github;

import com.winchesters.devopsify.dto.GithubRepositoryDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GithubRepositoryImpl implements GithubRepository {
    private final GithubServiceImpl githubService;

    @Override
    public GHRepository createRepository(@NotNull String personalAccessToken,
                                         GithubRepositoryDto githubRepositoryDto
    ) throws IOException {
        githubService.connectToGithub(personalAccessToken);
        return githubService.github.createRepository(githubRepositoryDto.name())
                .autoInit(Optional.ofNullable(githubRepositoryDto.autoInit()).orElse(false))
                .licenseTemplate(githubRepositoryDto.licenseTemplate())
                .gitignoreTemplate(githubRepositoryDto.gitIgnoreTemplate())
                .owner(githubRepositoryDto.owner())
                .create();
    }
}
