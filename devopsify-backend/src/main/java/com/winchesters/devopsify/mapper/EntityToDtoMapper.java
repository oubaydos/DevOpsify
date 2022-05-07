package com.winchesters.devopsify.mapper;

import com.winchesters.devopsify.dto.ProjectDto;
import com.winchesters.devopsify.dto.UserResponseDto;
import com.winchesters.devopsify.model.Project;
import com.winchesters.devopsify.model.User;

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
                (project.getJenkinsServer()!=null)?project.getJenkinsServer().getUrl():null,
                (project.getNexusServer()!=null)?project.getNexusServer().getUrl():null
        );
    }

    public static List<ProjectDto> ProjectToProjectDto(Collection<Project> projects) {
        return projects.stream().map(EntityToDtoMapper::ProjectToProjectDto).collect(Collectors.toList());
    }

    public static UserResponseDto userToUserResponseDto(User user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        );
    }
    public static List<UserResponseDto> userToUserResponseDto(Collection<User> users) {
        return users.stream().map(EntityToDtoMapper::userToUserResponseDto).collect(Collectors.toList());

    }
}
