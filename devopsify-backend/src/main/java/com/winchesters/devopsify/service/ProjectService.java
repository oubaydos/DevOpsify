package com.winchesters.devopsify.service;

import com.winchesters.devopsify.dto.CreateNewProjectDto;
import com.winchesters.devopsify.dto.ProjectDto;
import com.winchesters.devopsify.exception.ProjectNotFoundException;
import com.winchesters.devopsify.mapper.EntityToDtoMapper;
import com.winchesters.devopsify.model.entity.Project;
import com.winchesters.devopsify.model.entity.Server;
import com.winchesters.devopsify.repository.ProjectRepository;
import com.winchesters.devopsify.technologies.git.GitService;
import com.winchesters.devopsify.technologies.jenkins.JenkinsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final JenkinsService jenkinsService;
    private final GitService gitService;


    public List<ProjectDto> listProjects() {
        return EntityToDtoMapper.ProjectToProjectDto(projectRepository.findAll());
    }

    public ProjectDto getProject(Long projectId) {
        return EntityToDtoMapper.ProjectToProjectDto(
                projectRepository.findById(projectId)
                        .orElseThrow(ProjectNotFoundException::new)
        );
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

    @Transactional
    public void updateJenkinsServer(Long projectId, Server jenkinsServer) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        //TODO: encode password before saving it

        jenkinsService.setJenkinsClient(jenkinsServer);
        jenkinsService.pingJenkinsServer();
//        jenkinsService.installPlugins();
//        jenkinsService.createApiToken();

        project.setJenkinsServer(jenkinsServer);

    }

    @Transactional
    public void setNexusServer(Long projectId, Server nexusServer) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
        //TODO: encode password before saving it
        project.setNexusServer(nexusServer);
    }
}
