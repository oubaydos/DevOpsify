package com.winchesters.devopsify.service.technologies.github;

import com.winchesters.devopsify.dto.request.GithubRepositoryDto;
import com.winchesters.devopsify.enums.RepositoryStatus;
import com.winchesters.devopsify.enums.ReadMeStatus;
import com.winchesters.devopsify.exception.github.GithubRepositoryNotFoundException;
import com.winchesters.devopsify.exception.github.PersonalAccessTokenPermissionException;
import com.winchesters.devopsify.model.GithubAnalyseResults;
import com.winchesters.devopsify.model.entity.Project;
import com.winchesters.devopsify.service.UserService;
import com.winchesters.devopsify.service.technologies.docker.repositorydocker.DockerRepositoryAnalyser;
import com.winchesters.devopsify.service.technologies.github.branch.Branch;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

import static com.winchesters.devopsify.service.technologies.github.readme.ReadMe.analyseReadMe;

@Service
@RequiredArgsConstructor
@Transactional
public class GithubRepositoryServiceImpl implements GithubRepositoryService {
    //TODO : add github after analysis actions
    private final GithubServiceImpl githubService;
    private final UserService userService;

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
            throw new GithubRepositoryNotFoundException();
        GHContent inputStream = repository.getReadme();
        if (inputStream.getSize() == 0) {
            return "";
        }
        byte[] bytes = new byte[Math.toIntExact(inputStream.getSize())];
        //noinspection ResultOfMethodCallIgnored
        inputStream.read().read(bytes);
        return new String(bytes);
    }

    private ReadMeStatus getReadMeStatus(GHRepository repository) throws IOException {
        String readMeContent = getReadMe(repository);
        LOG.debug("ReadMe's number of lines : {}", readMeContent.lines().count());
        return analyseReadMe(readMeContent, repository.getName());
    }
    private RepositoryStatus getRepositoryStatus(GHRepository repository) throws IOException {
        if (repository == null)
            throw new GithubRepositoryNotFoundException();
        if (repository.getLicense() == null)
            return RepositoryStatus.LICENSE_MISSING;
        Branch branch = new Branch(repository);
        if (!branch.containsGitIgnore()) return RepositoryStatus.GITIGNORE_MISSING;
        if (!getReadMeStatus(repository).equals(ReadMeStatus.OKAY)) return RepositoryStatus.README_PROBLEM;
        return RepositoryStatus.OKAY;
    }

    private GithubAnalyseResults analyseGithub(GHRepository repository) throws IOException {
        return new GithubAnalyseResults(
                repository.getName(),
                getReadMeStatus(repository),
                getRepositoryStatus(repository),
                repository.listCommits().toList().size(),
                repository.getPushedAt()
        );
    }

    public GithubAnalyseResults analyseGithub(Project project) throws IOException {
        /*
         * name = username/repo-name
         */
        // TODO : this approach of getting the username will work only if i am the owner of the repository, if not, i must find a new method
        String name = userService.getGithubCredentials().username() + "/" + project.getName();
        GHRepository repository = githubService.getGithub().getRepository(name);
        return analyseGithub(repository);
    }


    // IGNORE
    public GithubAnalyseResults test(String name) throws IOException {


        GitHub github = githubService.getGithub();
        GHRepository repository = github.getRepository("temp-devopsify/"+name);
        if (repository == null)
            throw new GithubRepositoryNotFoundException();
        DockerRepositoryAnalyser dockerRepositoryAnalyser = new DockerRepositoryAnalyser(repository);
        LOG.debug("number of docker files : {}",dockerRepositoryAnalyser.numberOfDockerComposeFiles());
        return new GithubAnalyseResults("number of docker files : "+dockerRepositoryAnalyser.numberOfDockerComposeFiles(),ReadMeStatus.OKAY,RepositoryStatus.OKAY,1, Date.from(Instant.now()));


    }

}
