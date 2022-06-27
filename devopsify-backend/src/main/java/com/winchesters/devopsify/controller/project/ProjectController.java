package com.winchesters.devopsify.controller.project;

import com.winchesters.devopsify.dto.request.GenerateMavenProjectDto;
import com.winchesters.devopsify.dto.request.ProjectDto;
import com.winchesters.devopsify.dto.request.project.CreateNewProjectDto;
import com.winchesters.devopsify.dto.request.project.CreateNewProjectWithInitDto;
import com.winchesters.devopsify.model.AnalyseResults;
import com.winchesters.devopsify.model.entity.Server;
import com.winchesters.devopsify.service.ProjectService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * retrieves the list of projects owned by the authenticated user
     * @return list of projects
     */
    @GetMapping
    public List<ProjectDto> listProjects(){
        return projectService.listProjects();
    }


    /**
     * get project details
     * @param projectId id of the project
     * @return the project with the given id
     */
    @GetMapping("{projectId}")
    public ProjectDto getProject(@PathVariable Long projectId){
        return projectService.getProjectDto(projectId);
    }

    @PostMapping
    @Deprecated
    public ProjectDto createNewProject(@RequestBody CreateNewProjectDto createNewProjectDto){
        return projectService.createNewProject(createNewProjectDto);
    }

    /**
     * create new project
     * @param createNewProjectWithInitDto create new project request dto
     * @return instance of projectDto of the created project
     * @throws GitAPIException if there is any problem connecting to the remote repository
     * @throws IOException if there is an I/O problem
     * @throws InterruptedException if the process is interrupted
     */
    @PostMapping("/init")
    public ProjectDto createNewProjectWithInit(@RequestBody CreateNewProjectWithInitDto createNewProjectWithInitDto) throws GitAPIException, IOException, InterruptedException {
        return projectService.createNewProjectWithInit(createNewProjectWithInitDto);
    }

    /**
     * update jenkins server
     * @param projectId Long The id of the project
     * @param jenkinsServer Server containing the server's url , and credentials of an admin user
     */
    @PostMapping("{projectId}/jenkins")
    public void updateJenkinsServer(@PathVariable Long projectId, @RequestBody Server jenkinsServer){
        projectService.updateJenkinsServer(projectId,jenkinsServer);
    }

    /**
     * set nexus server
     * @param projectId Long The id of the project
     * @param nexusServer Server containing the server's url , and credentials of an admin user
     */
    @PostMapping("{projectId}/nexus-server")
    public void setNexusServer(@PathVariable Long projectId, @RequestBody Server nexusServer){
        projectService.setNexusServer(projectId,nexusServer);
    }


    /**
     * does a DevOps analysis of the project
     * @param projectId Long The id of the project
     * @return AnalyseResults an object containing the results of the analysis
     * @throws IOException if there is an I/O problem
     * @throws GitAPIException if there is any problem connecting to the remote repository
     */
    @GetMapping("{projectId}/analyse")
    public AnalyseResults analyse(@PathVariable Long projectId) throws IOException, GitAPIException {
        return projectService.analyse(projectId);
    }

    /**
     *
     * @param generateMavenProjectDto dto containing parameters that will be used to generate a Maven project
     * @param projectId Long The id of the project
     * @throws IOException if there is an I/O problem
     * @throws InterruptedException if the process is interrupted
     */
    @PostMapping("{projectId}/maven")
    void generateMavenProject(@ModelAttribute GenerateMavenProjectDto generateMavenProjectDto, @PathVariable Long projectId) throws IOException, InterruptedException {
        projectService.generateMavenProject(generateMavenProjectDto,projectId);
    }

}
