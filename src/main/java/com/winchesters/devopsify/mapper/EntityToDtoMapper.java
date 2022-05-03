package com.winchesters.devopsify.mapper;

import com.winchesters.devopsify.dto.ProjectDto;
import com.winchesters.devopsify.model.Project;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntityToDtoMapper {
    public static ProjectDto ProjectToProjectDto(Project project) {

        return new ProjectDto(
                project.getProjectId(),
                project.getName(),
                //TODO
                "todo",
                project.getRemoteRepoUrl(),
                project.getLocalRepoPath(),
                project.getIsGitInitialized(),
                project.getIsMavenProject(),
                project.getIsDockerized(),
                project.getHasJenkinsFile(),
                project.getHasTests(),
                project.getJenkinsServer().getUrl(),
                project.getNexusServer().getUrl()
        );
    }

    public static List<ProjectDto> ProjectToProjectDto(Collection<Project> projects) {
        return projects.stream().map(EntityToDtoMapper::ProjectToProjectDto).collect(Collectors.toList());
    }
}
