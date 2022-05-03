package com.winchesters.devopsify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectDto {
    private Long projectId;
    private String name;
    private String imageUrl;
    private String remoteRepoUrl;
    private String localRepoPath;
    private Boolean isGitInitialized;
    private Boolean isMavenProject;
    private Boolean isDockerized;
    private Boolean hasJenkinsFile;
    private Boolean hasTests;
    private String jenkinsServerUrl;
    private String nexusServerUrl;
}
