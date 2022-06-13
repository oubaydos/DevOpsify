package com.winchesters.devopsify.service.technologies.github;

import com.winchesters.devopsify.dto.request.GithubRepositoryDto;
import com.winchesters.devopsify.enums.ReadMeStatus;
import com.winchesters.devopsify.enums.RepositoryStatus;
import com.winchesters.devopsify.exception.github.GithubNotContainingLoginException;
import com.winchesters.devopsify.exception.github.GithubRepositoryNotFoundException;
import com.winchesters.devopsify.exception.github.PersonalAccessTokenPermissionException;
import com.winchesters.devopsify.model.GithubAnalyseResults;
import com.winchesters.devopsify.model.entity.Project;
import com.winchesters.devopsify.service.UserService;
import com.winchesters.devopsify.service.technologies.github.branch.Branch;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

import static com.winchesters.devopsify.service.technologies.github.readme.ReadMe.analyseReadMe;
import static com.winchesters.devopsify.utils.Utils.addTrailingSlash;
import static com.winchesters.devopsify.utils.Utils.toGithubRepositoryName;

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

    /**
     * @param name only title, gets username from current user
     * @return the full name
     * @deprecated
     */
    @Deprecated
    private String getRepositoryName(String name) {
        return userService.getGithubCredentials().username() + "/" + toGithubRepositoryName(name);
    }

    /**
     * @param name only title, gets username from current user
     * @return the full name
     * @deprecated
     */
    @Deprecated
    private GHRepository getRepository(String name) throws IOException {
        LOG.debug(getRepositoryName(name));
        return githubService.getGithub().getRepository(getRepositoryName(name));
    }


    // IGNORE
    public GithubAnalyseResults test(String name) throws IOException {


        GitHub github = githubService.getGithub();
        deleteAllRepositories();
        return null;
//
//        GHRepository repository = github.getRepository("temp-devopsify/" + name);
//        if (repository == null)
//            throw new GithubRepositoryNotFoundException();
//        DockerRepositoryAnalyser dockerRepositoryAnalyser = new DockerRepositoryAnalyser(repository);
//        LOG.debug("number of docker files : {}", dockerRepositoryAnalyser.numberOfDockerComposeFiles());
//        return new GithubAnalyseResults("number of docker files : " + dockerRepositoryAnalyser.numberOfDockerComposeFiles(), ReadMeStatus.OKAY, RepositoryStatus.OKAY, 1, Date.from(Instant.now()));


    }

    /**
     * deletes a github repository
     *
     * @param owner the repository owner's name
     * @param name  the name of the repository
     * @throws IOException when io exceptions
     */
    public void deleteGithubRepository(String owner, String name) throws IOException {
        this.githubService.getGithub().getRepository(owner + "/" + name).delete();
    }

    /**
     * @param name the name of the repository
     * @throws IOException when io exceptions
     * @deprecated please set the owner's name
     */
    @Deprecated
    public void deleteGithubRepository(String name) throws IOException {
        String username = getGithubUsername();
        this.deleteGithubRepository(username, name);
    }

    /**
     * @param username github username
     * @throws IOException when io exceptions
     */
    public void deleteAllRepositories(String username) throws IOException {
        for (GHRepository repository :
                githubService
                        .getGithub()
                        .searchRepositories()
                        .user(username)
                        .list()
                        .toList()
        ) {
            repository.delete();
        }
    }

    /**
     * @throws IOException when io exceptions
     * @deprecated uses the username in github object that may not exist
     */
    @Deprecated
    public void deleteAllRepositories() throws IOException {
        GitHub gitHub = this.githubService.getGithub();
        for (GHRepository repository :
                gitHub
                        .searchRepositories()
                        .user(getGithubUsername())
                        .list()
                        .toList()
        ) {
            repository.delete();
        }
    }

    private String getGithubUsername() throws IOException {
        GHMyself myself = this.githubService.getGithub().getMyself();
        if (myself == null || myself.getLogin() == null)
            throw new GithubNotContainingLoginException();
        return myself.getLogin();
    }

    public void createWebHook(Project project, String token) throws IOException {
        String webHookUrl = addTrailingSlash(project.getJenkinsServer().url()) + "multibranch-webhook-trigger/invoke?token=" + token;
        this.getRepository(project.getName()).createWebHook(new URL(webHookUrl));
    }

    public static void main(String[] args) throws IOException {
        GitHub gitHub = new GitHubBuilder().withOAuthToken("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").build();
        System.out.println(gitHub.getRepository("temp-devopsify/" + toGithubRepositoryName("russian hfrehh")).getHooks());
    }
}
