package com.winchesters.devopsify.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
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
