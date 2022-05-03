package com.winchesters.devopsify.service;

import com.winchesters.devopsify.dto.CreateNewProjectDto;
import com.winchesters.devopsify.exception.ProjectNotFoundException;
import com.winchesters.devopsify.model.Project;
import com.winchesters.devopsify.repository.ProjectRepository;
import com.winchesters.devopsify.technologies.git.GitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    
    private final ProjectRepository projectRepository;
    private final GitService gitService;


    public List<Project> listProjects() {
        return projectRepository.findAll();
    }

    public Project getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(ProjectNotFoundException::new);
    }

    public Project createNewProject(CreateNewProjectDto createNewProjectDto) {
        Project project = new Project();
        project.setName(createNewProjectDto.name());
        project.setLocalRepoPath(
                createNewProjectDto.location()+"/"+createNewProjectDto.name()
        );
        if(createNewProjectDto.initGitRepository()) {
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
