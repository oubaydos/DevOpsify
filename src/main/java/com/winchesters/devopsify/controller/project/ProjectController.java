package com.winchesters.devopsify.controller.project;

import com.winchesters.devopsify.dto.CreateNewProjectDto;
import com.winchesters.devopsify.model.Project;
import com.winchesters.devopsify.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> listProjects(){
        return projectService.listProjects();
    }
    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable Long projectId){
        return projectService.getProject(projectId);
    }

    @PostMapping
    public Project createNewProject(@RequestBody CreateNewProjectDto createNewProjectDto){
        return projectService.createNewProject(createNewProjectDto);
    }

}
