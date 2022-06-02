package com.winchesters.devopsify.service.technologies.git;

import com.winchesters.devopsify.exception.git.GitException;
import com.winchesters.devopsify.exception.git.GitInternalException;
import com.winchesters.devopsify.exception.git.GitNotInstalledException;
import com.winchesters.devopsify.model.GithubAnalyseResults;
import com.winchesters.devopsify.model.GithubCredentials;
import lombok.RequiredArgsConstructor;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class GitServiceImpl implements GitService {

    private final Logger LOG = LoggerFactory.getLogger(GitServiceImpl.class);

    public static void main(String[] args) throws GitAPIException, IOException {
        String localPath = "/home/hamza/test/devopsify_test";
        String remoteUrl = "https://github.com/HamzaBenyazid/devopsify-testing";
        GithubCredentials credentials = new GithubCredentials(
                "HamzaBenyazid",
                "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"
        );
        new GitServiceImpl().pullOriginMain(credentials,localPath);
    }

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
            if (!installed()) throw new GitNotInstalledException();
            new ProcessBuilder("git", "init")
                    .directory(new File(path))
                    .inheritIO()
                    .start();
        } catch (IOException e) {
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
    public void pullOriginMain(GithubCredentials credentials,String path) throws GitException {
        pull(credentials,path, "origin", "main");
    }

    @Override
    public void pull(GithubCredentials credentials, String path, String remoteRepoName, String remoteBranchName) {
        try {
            Repository repository = getRepository(path);
            Git git = new Git(repository);
            PullCommand pull = git.pull()
                    .setRemote(remoteRepoName)
                    .setRemoteBranchName(remoteBranchName)
                    .setCredentialsProvider(
                            new UsernamePasswordCredentialsProvider(
                                    credentials.username(),
                                    credentials.personalAccessToken()
                            )
                    );
            PullResult result = pull.call();
            if (!result.isSuccessful()) {
                LOG.error(String.format("Cannot pull from '%s', branch '%s'",remoteRepoName,remoteBranchName));
                Status status = git.status().call();
                LOG.error("git status cleanliness: " + status.isClean());
                if (!status.isClean()) {
                    LOG.debug("git status uncommitted changes: " + status.getUncommittedChanges());
                    LOG.debug("git status untracked: " + status.getUntracked());
                }
            } else {
                LOG.info("Pull was successful.");
            }
        } catch (GitAPIException e) {
            throw new com.winchesters.devopsify.exception.git.GitAPIException(e);
        } catch (IOException e){
            throw new GitException(e);
        }
    }

    @Override
    public void clone(String remoteUrl, String localPath, GithubCredentials credentials) {
        final File localPathFile = new File(localPath);
        try {
            Git.cloneRepository()
                    .setURI(remoteUrl)
                    .setDirectory(localPathFile)
                    .setCredentialsProvider(
                            new UsernamePasswordCredentialsProvider(
                                    credentials.username(),
                                    credentials.personalAccessToken()
                            )
                    )
                    .call();
        } catch (JGitInternalException e) {
            throw new GitInternalException(e);
        } catch (GitAPIException e) {
            throw new com.winchesters.devopsify.exception.git.GitAPIException(e);
        }
    }

    @Override
    public Repository getRepository(String path) throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        try (Repository repository = builder.setGitDir(new File(path + "/.git"))
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build()) {
            LOG.debug(repository.isBare() ? "true" : "false");
            return repository;
        }
    }
}
