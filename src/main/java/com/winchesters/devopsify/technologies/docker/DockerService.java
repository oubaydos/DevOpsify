package com.winchesters.devopsify.technologies.docker;

import com.winchesters.devopsify.technologies.TechnologyService;

public interface DockerService extends TechnologyService {
    boolean dockerInstalled();
    boolean dockerComposeInstalled();


}
