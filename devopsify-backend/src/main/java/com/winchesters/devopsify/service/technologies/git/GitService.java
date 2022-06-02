package com.winchesters.devopsify.service.technologies.git;

import com.winchesters.devopsify.exception.git.GitException;
import com.winchesters.devopsify.model.GithubAnalyseResults;
import com.winchesters.devopsify.model.GithubCredentials;
import com.winchesters.devopsify.service.technologies.TechnologyService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

public interface GitService extends TechnologyService {

    void initializeRepository(String path) throws GitException;

    Boolean remoteAndLocalInSync() throws GitException;

    GithubAnalyseResults analyseGithub();

    void pullOriginMain(GithubCredentials credentials,String path) throws GitException;

    void pull(GithubCredentials credentials,String path, String remoteRepoName, String remoteBranchName) throws GitException;

    void clone(String remoteUrl, String localPath, GithubCredentials credentials) throws GitException, IOException, GitAPIException;

    Repository getRepository(String path) throws IOException;

    void push(GithubCredentials credentials,String path, String remoteRepoName, String remoteBranchName) throws GitException;

    void pushOriginMain(GithubCredentials credentials,String path) throws GitException;

    void add(String path,String filePattern);

    void addAll(String path);

    void commit(String path,String message);

    void commitAll(String path,String message);

}
