package com.winchesters.devopsify.service.technologies.nexus;

import com.winchesters.devopsify.model.NexusAnalyseResults;
import org.springframework.stereotype.Service;

@Service
public class NexusServiceImpl implements NexusService{

    @Override
    public void pingNexusServer(String serverUrl) {
    }

    @Override
    public NexusAnalyseResults analyseNexus() {
        //TODO
        return null;
    }
}
