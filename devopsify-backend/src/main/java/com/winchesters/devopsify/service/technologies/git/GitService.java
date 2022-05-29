package com.winchesters.devopsify.service.technologies.git;

import com.winchesters.devopsify.exception.git.GitException;
import com.winchesters.devopsify.model.GithubAnalyseResults;
import com.winchesters.devopsify.service.technologies.TechnologyService;

public interface GitService extends TechnologyService {

    void initializeRepository(String path) throws GitException;

    Boolean remoteAndLocalInSync() throws GitException;

    GithubAnalyseResults analyseGithub();

    void pull(String path);

    String clone(String remoteUrl,String localPath);
}
