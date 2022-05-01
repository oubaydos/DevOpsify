package com.winchesters.devopsify.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    private String name;
    private String imagePath;
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
