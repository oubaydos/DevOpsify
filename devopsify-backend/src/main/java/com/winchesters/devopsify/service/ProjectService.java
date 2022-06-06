package com.winchesters.devopsify.service;

import com.winchesters.devopsify.dto.request.*;
import com.winchesters.devopsify.exception.UserCredentialsNotFoundException;
import com.winchesters.devopsify.exception.project.ProjectNotFoundException;
import com.winchesters.devopsify.mapper.EntityToDtoMapper;
import com.winchesters.devopsify.model.AnalyseResults;
import com.winchesters.devopsify.model.GithubCredentials;
import com.winchesters.devopsify.model.entity.Project;
import com.winchesters.devopsify.model.entity.Server;
import com.winchesters.devopsify.model.entity.User;
import com.winchesters.devopsify.repository.ProjectRepository;
import com.winchesters.devopsify.service.technologies.git.GitService;
import com.winchesters.devopsify.service.technologies.github.GithubRepositoryServiceImpl;
import com.winchesters.devopsify.service.technologies.jenkins.JenkinsService;
import com.winchesters.devopsify.service.technologies.maven.MavenService;
import com.winchesters.devopsify.service.technologies.nexus.NexusService;
import lombok.RequiredArgsConstructor;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

import static com.winchesters.devopsify.utils.IOUtils.projectsDirectory;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final JenkinsService jenkinsService;
    private final GitService gitService;
    private final GithubRepositoryServiceImpl githubRepositoryService;
    private final NexusService nexusService;

    private final UserService userService;

    private final MavenService mavenService;

    public Project findProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);
    }

    public List<ProjectDto> listProjects() {
        return EntityToDtoMapper.ProjectToProjectDto(projectRepository.findAll());
    }

    public ProjectDto getProjectDto(Long projectId) {
        return EntityToDtoMapper.ProjectToProjectDto(
                projectRepository.findById(projectId)
                        .orElseThrow(ProjectNotFoundException::new)
        );
    }

    public Project getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
    }

    public ProjectDto createNewProjectWithInit(CreateNewProjectWithInitDto createNewProjectWithInitDto) throws IOException, GitAPIException {
        GithubRepositoryDto githubRepositoryDto = new GithubRepositoryDto(createNewProjectWithInitDto);
        GHRepository ghRepository = githubRepositoryService.createRepository(githubRepositoryDto);
        String localPath = projectsDirectory() + "/" + createNewProjectWithInitDto.name();
        gitService.clone(userService.getGithubCredentials(),ghRepository.getHtmlUrl().toString(), localPath);
        Project project = new Project();
        project.setName(createNewProjectWithInitDto.name());
        project.setLocalRepoPath(localPath);
        project.setRemoteRepoUrl(ghRepository.getHtmlUrl().toString());
        return EntityToDtoMapper.ProjectToProjectDto(projectRepository.save(project));
    }


    public ProjectDto createNewProject(CreateNewProjectDto createNewProjectDto) {
        Project project = new Project();
        project.setName(createNewProjectDto.name());
        project.setLocalRepoPath(
                createNewProjectDto.location() + "/" + createNewProjectDto.name()
        );
        if (createNewProjectDto.initGitRepository()) {
            gitService.initializeRepository(project.getLocalRepoPath());
            project.setIsGitInitialized(true);
        } else {
            project.setIsGitInitialized(false);
        }
        project.setIsMavenProject(false);
        project.setHasJenkinsFile(false);
        project.setIsDockerized(false);

        return EntityToDtoMapper.ProjectToProjectDto(projectRepository.save(project));
    }

    public void updateJenkinsServer(Long projectId, Server jenkinsServer) {
        Project project = findProjectById(projectId);
        //TODO: encode password before saving it

        jenkinsService.setJenkinsClient(jenkinsServer);
        jenkinsService.pingJenkinsServer();
        jenkinsService.installPlugins();
        jenkinsService.createApiToken();

        project.setJenkinsServer(jenkinsServer);
    }

    public void setNexusServer(Long projectId, Server nexusServer) {
        Project project = findProjectById(projectId);
        //TODO: encode password before saving it
        project.setNexusServer(nexusServer);
    }

    public AnalyseResults analyse(Long projectId) throws IOException {
        User user = userService.getCurrentUser();
        GithubCredentials githubCredentials = user.getGithubCredentials();

        if (githubCredentials == null) {
            throw new UserCredentialsNotFoundException();
        }

        Project project = findProjectById(projectId);
        if (gitService.localAndOriginMainInSync(githubCredentials, project.getLocalRepoPath())) {
            return project.getAnalyseResults();
        }

        gitService.syncLocalWithOriginMain(githubCredentials, project.getLocalRepoPath());

        AnalyseResults analyseResults = new AnalyseResults(
                githubRepositoryService.analyseGithub(getProject(projectId)),
                jenkinsService.analyseJenkins(getProject(projectId)),
                nexusService.analyseNexus(getProject(projectId))
        );

        project.setAnalyseResults(analyseResults);

        return analyseResults;
    }

    public void generateMavenProject(GenerateMavenProjectDto dto, Long projectId) {
        Project project = findProjectById(projectId);
        mavenService.generateMavenProject(dto,project.getLocalRepoPath());
    }
}
