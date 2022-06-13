package com.winchesters.devopsify.dto.request.project;

import com.winchesters.devopsify.dto.request.GenerateMavenProjectDto;

public record CreateNewProjectWithInitDto(

        CreateNewProjectGeneralDto general,
        CreateNewProjectGithubDto github,
        GenerateMavenProjectDto maven,

        CreateNewProjectDockerDto docker,
        CreateNewProjectJenkinsDto jenkins,
        CreateNewProjectNexusDto nexus,
        CreateNewProjectEc2Dto ec2
) {
}
