package com.winchesters.devopsify.service;

import com.winchesters.devopsify.dto.request.GenerateMavenProjectDto;
import com.winchesters.devopsify.dto.request.GithubRepositoryDto;
import com.winchesters.devopsify.dto.request.ProjectDto;
import com.winchesters.devopsify.dto.request.project.CreateNewProjectDockerDto;
import com.winchesters.devopsify.dto.request.project.CreateNewProjectDto;
import com.winchesters.devopsify.dto.request.project.CreateNewProjectWithInitDto;
import com.winchesters.devopsify.exception.UserCredentialsNotFoundException;
import com.winchesters.devopsify.exception.project.ProjectNotFoundException;
import com.winchesters.devopsify.mapper.EntityToDtoMapper;
import com.winchesters.devopsify.model.AnalyseResults;
import com.winchesters.devopsify.model.GithubCredentials;
import com.winchesters.devopsify.model.entity.Project;
import com.winchesters.devopsify.model.entity.Server;
import com.winchesters.devopsify.model.entity.User;
import com.winchesters.devopsify.repository.ProjectRepository;
import com.winchesters.devopsify.service.technologies.GeneratedFile;
import com.winchesters.devopsify.service.technologies.docker.systemdocker.DockerService;
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

import static com.winchesters.devopsify.utils.DockerfileUtils.backendDockerfileDtoToBackendDockerFile;
import static com.winchesters.devopsify.utils.DockerfileUtils.dataBaseDockerfileDtoToDataBaseDockerFile;
import static com.winchesters.devopsify.utils.IOUtils.projectsDirectory;
import static com.winchesters.devopsify.utils.JenkinsfileUtils.jenkinsFileDtoToJenkinsFile;

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

    private final DockerService dockerService;

    public Project findProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(ProjectNotFoundException::new);
    }

    public List<ProjectDto> listProjects() {
        User user = userService.getCurrentUser();
        return EntityToDtoMapper.ProjectToProjectDto(user.getProjects());
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

    public ProjectDto createNewProjectWithInit(CreateNewProjectWithInitDto dto) throws IOException, GitAPIException, InterruptedException {


        String localRepoPath = projectsDirectory() + "/" + dto.general().name();

        //github
        GithubRepositoryDto githubRepositoryDto = new GithubRepositoryDto(dto.general(), dto.github());
        GHRepository ghRepository = githubRepositoryService.createRepository(githubRepositoryDto);

        //git
        gitService.clone(userService.getGithubCredentials(), ghRepository.getHtmlUrl().toString(), localRepoPath);

        //maven
        mavenService.generateMavenProject(dto.maven(), localRepoPath);


        //docker
        CreateNewProjectDockerDto dockerDto = dto.docker();
        if (dockerDto.dockerizeBackend()) {
            generateGeneratedFile(
                    localRepoPath,
                    localRepoPath,
                    backendDockerfileDtoToBackendDockerFile(dockerDto.dockerBackend(), dockerDto.defaultDockerBackend(), dto.maven().artifactId()),
                    "generate backend dockerfile"
            );
        }
        if (dockerDto.dockerizeDB()) {
            generateGeneratedFile(
                    localRepoPath,
                    localRepoPath + "/db",
                    dataBaseDockerfileDtoToDataBaseDockerFile(dockerDto.dockerDB(), dockerDto.defaultDockerDB()),
                    "generate db dockerfile"
            );
        }
        //saving project in database
        Project project = new Project();
        project.setName(dto.general().name());
        project.setLocalRepoPath(localRepoPath);
        project.setRemoteRepoUrl(ghRepository.getHtmlUrl().toString());
        //TODO : jenkins
        // IMAGE_NAME is not being set in jenkinsfile
        if (dto.jenkins().generateJenkinsfile()) {
            generateGeneratedFile(
                    localRepoPath,
                    localRepoPath,
                    jenkinsFileDtoToJenkinsFile(dto.jenkins().jenkinsfile(), dto.maven().artifactId(), project.getRemoteRepoUrl()),
                    "generate Jenkinsfile"
            );
        }
        project.setJenkinsServer(dto.jenkins().server());
        /*
         ssh key with id
         */
        // TODO must get it from front
//        Server dockerhubCredentials = new Server("dockerhub", "", "");
//        Server ec2Credentials = new Server("ec2", "", "");
        jenkinsService.createJenkinsPipeline(
                dto.jenkins().server(),
                project.getName(),
                project.getRemoteRepoUrl(),
                dto.nexus().server(),
                dto.ec2().server()
        );
        //TODO : nexus


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
        jenkinsService.installRequiredPlugins();
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

    public void generateMavenProject(GenerateMavenProjectDto dto, Long projectId) throws IOException, InterruptedException {
        Project project = findProjectById(projectId);
        mavenService.generateMavenProject(dto, project.getLocalRepoPath());
    }


    public void generateGeneratedFile(String repo, String path,
                                      GeneratedFile file,
                                      String commitMsg) throws IOException {
        User user = userService.getCurrentUser();
        gitService.syncLocalWithOriginMain(user.getGithubCredentials(), repo);
        file.writeFile(path);
        gitService.commitAll(repo, commitMsg);
        gitService.pushOriginMain(user.getGithubCredentials(), repo);
    }
}
