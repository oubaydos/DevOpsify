package com.winchesters.devopsify.model;

import lombok.*;
import org.hibernate.annotations.Type;

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

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private Server jenkinsServer;

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private Server nexusServer;
}
