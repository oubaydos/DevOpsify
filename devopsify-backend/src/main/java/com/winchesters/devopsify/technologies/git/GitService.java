package com.winchesters.devopsify.technologies.git;

import com.winchesters.devopsify.exception.git.GitException;
import com.winchesters.devopsify.technologies.TechnologyService;

public interface GitService extends TechnologyService {

    void initializeRepository(String path) throws GitException;

}
