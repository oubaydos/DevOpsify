package com.winchesters.devopsify.service.technologies.docker.systemdocker;

import com.winchesters.devopsify.dto.request.BackendDockerfileDto;
import com.winchesters.devopsify.dto.request.DataBaseDockerfileDto;
import com.winchesters.devopsify.service.technologies.TechnologyService;

import java.io.File;
import java.io.IOException;

public interface DockerService extends TechnologyService {
    boolean dockerInstalled();
    boolean dockerComposeInstalled();

    void generateDockerFile(File directory);

    void installDockerCompose();
    void installDocker();

    byte[] viewDataBaseDockerfile(DataBaseDockerfileDto dto) throws IOException;

    byte[] viewBackendDockerfile(BackendDockerfileDto dto) throws IOException;
}
