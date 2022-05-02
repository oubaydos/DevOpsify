package com.winchesters.devopsify.technologies.docker;

import com.winchesters.devopsify.technologies.TechnologyService;

import java.io.File;

public interface DockerService extends TechnologyService {
    boolean dockerInstalled();
    boolean dockerComposeInstalled();

    void generateDockerFile(File directory);


}
