package com.winchesters.devopsify.dto.request.project;

public record CreateNewProjectWithInitDto(

        CreateNewProjectGeneralDto general,
        CreateNewProjectGithubDto github,
        CreateNewProjectMavenDto maven,
        CreateNewProjectDockerDto docker,
        CreateNewProjectJenkinsDto jenkins,
        CreateNewProjectNexusDto nexus
) {
}
