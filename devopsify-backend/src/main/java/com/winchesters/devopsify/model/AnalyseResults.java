package com.winchesters.devopsify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseResults{
    private GithubAnalyseResults github;
    private JenkinsAnalyseResults jenkins;
    private NexusAnalyseResults nexus;
    //private DockerAnalyseResults docker;
    //private MavenAnalyseResults maven;
}
