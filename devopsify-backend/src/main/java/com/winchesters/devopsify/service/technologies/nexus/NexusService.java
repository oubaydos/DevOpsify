package com.winchesters.devopsify.service.technologies.nexus;

import com.winchesters.devopsify.model.NexusAnalyseResults;
import com.winchesters.devopsify.model.entity.Project;

public interface NexusService{
    void pingNexusServer(String serverUrl);

    NexusAnalyseResults analyseNexus(Project project);

}
