package com.winchesters.devopsify.controller.project;

import com.winchesters.devopsify.dto.request.CreateNewProjectWithInitDto;
import com.winchesters.devopsify.dto.request.CreateNewProjectDto;
import com.winchesters.devopsify.dto.request.ProjectDto;
import com.winchesters.devopsify.model.AnalyseResults;
import com.winchesters.devopsify.model.entity.Server;
import com.winchesters.devopsify.service.ProjectService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
        return projectService.getProjectDto(projectId);
    }

    @PostMapping
    public ProjectDto createNewProject(@RequestBody CreateNewProjectDto createNewProjectDto){
        return projectService.createNewProject(createNewProjectDto);
    }
    @PostMapping("/init")
    public ProjectDto createNewProjectWithInit(@RequestBody CreateNewProjectWithInitDto createNewProjectWithInitDto) throws GitAPIException, IOException {
        return projectService.createNewProjectWithInit(createNewProjectWithInitDto);
    }
    @PostMapping("{projectId}/jenkins")
    public void updateJenkinsServer(@PathVariable Long projectId, @RequestBody Server jenkinsServer){
        projectService.updateJenkinsServer(projectId,jenkinsServer);
    }

    @PostMapping("{projectId}/nexus-server")
    public void setNexusServer(@PathVariable Long projectId, @RequestBody Server nexusServer){
        projectService.setNexusServer(projectId,nexusServer);
    }

    @GetMapping("{projectId}/analyse")
    public AnalyseResults analyse(@PathVariable Long projectId) throws IOException {
        return projectService.analyse(projectId);
    }

}
