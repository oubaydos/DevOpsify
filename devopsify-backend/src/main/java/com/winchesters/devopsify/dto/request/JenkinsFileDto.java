package com.winchesters.devopsify.dto.request;

import lombok.Builder;
import lombok.Data;

import static com.winchesters.devopsify.enums.JenkinsfileGroovyScriptKeywords.*;
@Data
public class JenkinsFileDto {

    private String imageName = IMAGE_NAME.defaultValue();

    private String dockerhubUsername = DOCKERHUB_USERNAME.defaultValue();

    private String ec2Username = EC2_USERNAME.defaultValue();

    private String ec2Ip = EC2_IP.defaultValue();

    private String ec2ContainerPort = EC2_CONTAINER_PORT.defaultValue();

    private String ec2DeploymentPort = EC2_DEPLOYMENT_PORT.defaultValue();

    private String githubRepositoryUrl = GITHUB_REPOSITORY_URL.defaultValue();

    private Boolean withDeployment = true;
}
