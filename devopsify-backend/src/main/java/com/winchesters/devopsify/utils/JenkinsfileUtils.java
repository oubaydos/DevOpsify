package com.winchesters.devopsify.utils;

import com.winchesters.devopsify.dto.request.JenkinsFileDto;
import com.winchesters.devopsify.service.technologies.jenkins.JenkinsFile;

import java.io.File;
import java.io.IOException;

public class JenkinsfileUtils extends FilesUtils {
    public JenkinsfileUtils(File file) throws IOException {
        super(file, "//");
    }
    public static JenkinsFile jenkinsFileDtoToJenkinsFile(JenkinsFileDto dto, Boolean defaultJenkinsFile) {
        if (defaultJenkinsFile) {
            return JenkinsFile.builder().setWithDeployment(false).build();
        }
        return JenkinsFile.builder()
                .setImageName(dto.getImageName())
                .setDockerhubUsername(dto.getDockerhubUsername())
                .setEc2Username(dto.getEc2Username())
                .setEc2Ip(dto.getEc2Ip())
                .setEc2ContainerPort(dto.getEc2ContainerPort())
                .setEc2DeploymentPort(dto.getEc2DeploymentPort())
                .setWithDeployment(dto.getWithDeployment())
                .setGithubRepositoryUrl(dto.getGithubRepositoryUrl())
                .build();
    }
}
