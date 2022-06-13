package com.winchesters.devopsify.model.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.winchesters.devopsify.model.AnalyseResults;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Boolean isGitInitialized = true;
    private Boolean isMavenProject = false;
    private Boolean isDockerized = false;
    private Boolean hasJenkinsFile = false;
    private Boolean hasTests = false;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private AnalyseResults analyseResults;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Server jenkinsServer;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Server nexusServer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner")
    private User owner;

}
