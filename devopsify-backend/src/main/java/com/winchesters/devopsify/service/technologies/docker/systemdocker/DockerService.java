package com.winchesters.devopsify.service.technologies.docker.systemdocker;

import com.winchesters.devopsify.service.technologies.TechnologyService;

import java.io.File;

public interface DockerService extends TechnologyService {
    boolean dockerInstalled();
    boolean dockerComposeInstalled();

    void generateDockerFile(File directory);

    void installDockerCompose();
    void installDocker();

}
