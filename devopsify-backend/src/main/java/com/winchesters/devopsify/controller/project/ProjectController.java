package com.winchesters.devopsify.controller.project;

import com.winchesters.devopsify.dto.CreateNewProjectDto;
import com.winchesters.devopsify.dto.ProjectDto;
import com.winchesters.devopsify.model.entity.Server;
import com.winchesters.devopsify.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {
    private final ProjectService projectService;
    private final Logger LOG = LoggerFactory.getLogger(ProjectController.class);

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectDto> listProjects(){
        return projectService.listProjects();
    }
    @GetMapping("{projectId}")
    public ProjectDto getProject(@PathVariable Long projectId){
        return projectService.getProject(projectId);
    }

    @PostMapping
    public ProjectDto createNewProject(@ModelAttribute CreateNewProjectDto createNewProjectDto){
        return projectService.createNewProject(createNewProjectDto);
    }

    @PostMapping("{projectId}/jenkins-server")
    public void setJenkinsServer(@PathVariable Long projectId, @RequestBody Server jenkinsServer){
        projectService.setJenkinsServer(projectId,jenkinsServer);
    }

    @PostMapping("{projectId}/nexus-server")
    public void setNexusServer(@PathVariable Long projectId, @RequestBody Server nexusServer){
        projectService.setNexusServer(projectId,nexusServer);
    }

}
