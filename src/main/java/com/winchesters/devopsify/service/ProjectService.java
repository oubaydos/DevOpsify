package com.winchesters.devopsify.service;

import com.winchesters.devopsify.dto.CreateNewProjectDto;
import com.winchesters.devopsify.exception.ProjectNotFoundException;
import com.winchesters.devopsify.model.Project;
import com.winchesters.devopsify.repository.ProjectRepository;
import com.winchesters.devopsify.technologies.git.GitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    
    private final ProjectRepository projectRepository;
    private final GitService gitService;

    public ProjectService(ProjectRepository projectRepository, GitService gitService) {
        this.projectRepository = projectRepository;
        this.gitService = gitService;
    }

    public List<Project> listProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
    }

    public Project createNewProject(CreateNewProjectDto createNewProjectDto) {
        Project project = new Project();
        project.setName(createNewProjectDto.getName());
        project.setLocalRepoPath(
                createNewProjectDto.getLocation()+"/"+createNewProjectDto.getName()
        );
        if(createNewProjectDto.isInitGitRepository()) {
            gitService.initializeRepository(project.getLocalRepoPath());
            project.setIsGitInitialized(true);
        }else{
            project.setIsGitInitialized(false);
        }
        project.setIsMavenProject(false);
        project.setHasJenkinsFile(false);
        project.setIsDockerized(false);

        return projectRepository.save(project);
    }
}
