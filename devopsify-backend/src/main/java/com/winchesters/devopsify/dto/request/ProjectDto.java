package com.winchesters.devopsify.dto.request;

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
    private String owner;
    private String token;
    private String webHookToken;
}
