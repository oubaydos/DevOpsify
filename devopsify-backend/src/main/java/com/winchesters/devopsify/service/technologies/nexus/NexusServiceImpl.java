package com.winchesters.devopsify.service.technologies.nexus;

import com.winchesters.devopsify.model.NexusAnalyseResults;
import com.winchesters.devopsify.model.entity.Project;
import org.springframework.stereotype.Service;

@Service
public class NexusServiceImpl implements NexusService{

    @Override
    public void pingNexusServer(String serverUrl) {
    }

    @Override
    public NexusAnalyseResults analyseNexus(Project project) {
        //TODO
        return null;
    }
}
