package com.winchesters.devopsify.service.technologies.github;

import com.winchesters.devopsify.dto.request.GithubRepositoryDto;
import com.winchesters.devopsify.enums.repositoryStatus;
import com.winchesters.devopsify.enums.ReadMeStatus;
import com.winchesters.devopsify.exception.github.GithubRepositoryNotFound;
import com.winchesters.devopsify.exception.github.PersonalAccessTokenPermissionException;
import com.winchesters.devopsify.service.technologies.github.readme.ReadMe;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GithubRepositoryServiceImpl implements GithubRepositoryService {
    private final GithubServiceImpl githubService;
    private static final Logger LOG = LoggerFactory.getLogger(GithubRepositoryServiceImpl.class);

    @Override
    public GHRepository createRepository(GithubRepositoryDto githubRepositoryDto
    ) throws IOException {
        GitHub gitHub = githubService.getGithub();
        if (gitHub == null)
            throw new PersonalAccessTokenPermissionException(); //TODO -- a more specific exception
        return gitHub.createRepository(githubRepositoryDto.name())
                .autoInit(Optional.ofNullable(githubRepositoryDto.autoInit()).orElse(false))
                .licenseTemplate(githubRepositoryDto.licenseTemplate())
                .gitignoreTemplate(githubRepositoryDto.gitIgnoreTemplate())
                .owner(githubRepositoryDto.owner())
                .description(githubRepositoryDto.description())
                .visibility(githubRepositoryDto.visibility())
                .private_(Optional.ofNullable(githubRepositoryDto.private_()).orElse(false))
                .create();
    }

    private String getReadMe(GHRepository repository) throws IOException {
        if (repository == null)
            throw new GithubRepositoryNotFound();
        GHContent inputStream = repository.getReadme();
        if (inputStream.getSize() == 0) {
            return "";
        }
        byte[] bytes = new byte[Math.toIntExact(inputStream.getSize())];
        inputStream.read().read(bytes);
        return new String(bytes);
    }

    public ReadMeStatus getReadMeStatus(GHRepository repository) throws IOException {
        String readMeContent = getReadMe(repository);
        LOG.debug("ReadMe's number of lines : {}", readMeContent.lines().count());
        return ReadMe.analyseReadMe(readMeContent, repository.getName());
    }
    public repositoryStatus analyseRepository(GHRepository repository) throws IOException {
        if (repository == null)
            throw new GithubRepositoryNotFound();
        if (repository.getLicense() == null)
            return repositoryStatus.LICENSE_MISSING;
//        LOG.debug("license : {}",repository.getLicenseContent().read());
        return repositoryStatus.OKAY;
    }
}
