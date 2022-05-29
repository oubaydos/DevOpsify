package com.winchesters.devopsify.service.technologies.git;

import com.winchesters.devopsify.exception.git.GitException;
import com.winchesters.devopsify.exception.git.GitNotInstalledException;
import com.winchesters.devopsify.model.GithubAnalyseResults;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class GitServiceImpl implements GitService{

    private final Logger LOG = LoggerFactory.getLogger(GitServiceImpl.class);

    @Override
    public boolean installed() {
        return installed("git");
    }

    @Override
    public void install() {
        installFromScript("git/git_install.sh");
    }

    @Override
    public void initializeRepository(String path) throws GitException {
        try {
            if(!installed()) throw new GitNotInstalledException();
            new ProcessBuilder("git", "init")
                    .directory(new File(path))
                    .inheritIO()
                    .start();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean remoteAndLocalInSync() throws GitException {
        //TODO
        return false;
    }

    @Override
    public GithubAnalyseResults analyseGithub() {
        //TODO
        return null;
    }

    @Override
    public void pull(String path) {
        //TODO
    }

    @Override
    public String clone(String remoteUrl,String localPath) {
        //TODO
        // clones repo from remoteUrl to localPath
        return null;
    }

}
