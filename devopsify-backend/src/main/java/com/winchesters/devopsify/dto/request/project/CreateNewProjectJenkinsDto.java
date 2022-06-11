package com.winchesters.devopsify.dto.request.project;

import com.winchesters.devopsify.dto.request.JenkinsFileDto;
import com.winchesters.devopsify.model.entity.Server;

public record CreateNewProjectJenkinsDto(
        Server server,
        JenkinsFileDto jenkinsfile,
        Boolean generateJenkinsfile
) {

}
