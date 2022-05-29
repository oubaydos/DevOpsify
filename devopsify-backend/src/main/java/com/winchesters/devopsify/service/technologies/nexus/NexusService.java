package com.winchesters.devopsify.service.technologies.nexus;

import com.winchesters.devopsify.model.NexusAnalyseResults;

public interface NexusService{
    void pingNexusServer(String serverUrl);

    NexusAnalyseResults analyseNexus();

}
