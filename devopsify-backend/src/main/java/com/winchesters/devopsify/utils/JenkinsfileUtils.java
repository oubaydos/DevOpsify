package com.winchesters.devopsify.utils;

import com.winchesters.devopsify.dto.request.JenkinsFileDto;
import com.winchesters.devopsify.service.technologies.jenkins.JenkinsFile;

import java.io.File;
import java.io.IOException;

public class JenkinsfileUtils extends FilesUtils {
    public JenkinsfileUtils(File file) throws IOException {
        super(file, "//");
    }

    public static JenkinsFile jenkinsFileDtoToJenkinsFile(JenkinsFileDto dto, String artifactName, String remoteRepoUrl) {
        remoteRepoUrl = remoteRepoUrl.replaceFirst("^(http[s]?://www\\.|http[s]?://|www\\.)", "");
        JenkinsFile.JenkinsFileBuilder jenkinsFileBuilder = JenkinsFile.builder();
        if (!dto.getWithDeployment()) {
            return jenkinsFileBuilder
                    .setWithDeployment(false)
                    .setImageName(dto.getImageName())
                    .setDockerhubUsername(dto.getDockerhubUsername())
                    .setGithubRepositoryUrl(remoteRepoUrl)
                    .setArtifactName(artifactName)
                    .build();
        }
        return jenkinsFileBuilder
                .setImageName(dto.getImageName())
                .setDockerhubUsername(dto.getDockerhubUsername())
                .setEc2Username(dto.getEc2Username())
                .setEc2Ip(dto.getEc2Ip())
                .setEc2ContainerPort(dto.getEc2ContainerPort())
                .setEc2DeploymentPort(dto.getEc2DeploymentPort())
                .setWithDeployment(dto.getWithDeployment())
                .setGithubRepositoryUrl(remoteRepoUrl)
                .setArtifactName(artifactName)
                .build();
    }
}
