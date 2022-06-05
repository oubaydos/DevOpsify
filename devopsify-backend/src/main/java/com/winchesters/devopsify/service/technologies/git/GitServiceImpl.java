package com.winchesters.devopsify.service.technologies.git;

import com.winchesters.devopsify.exception.git.GitException;
import com.winchesters.devopsify.exception.git.GitInternalException;
import com.winchesters.devopsify.exception.git.GitNotInstalledException;
import com.winchesters.devopsify.model.GithubAnalyseResults;
import com.winchesters.devopsify.model.GithubCredentials;
import lombok.RequiredArgsConstructor;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.RemoteRefUpdate;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class GitServiceImpl implements GitService {

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
    public Boolean localAndRemoteInSync(GithubCredentials githubCredentials,String localRepoPath,String remoteName,String remoteBranchName) throws GitException {
        try {
            Repository repository = getRepository(localRepoPath);
            Git git = new Git(repository);

            String latestRemoteCommitHash = git.getRepository()
                    .exactRef(String.format("refs/remotes/%s/%s",remoteName, remoteBranchName))
                    .getObjectId()
                    .name();

            RevCommit latestLocalCommit = new Git(repository).
                    log().
                    setMaxCount(1).
                    call().
                    iterator().
                    next();

            String latestLocalCommitHash = latestLocalCommit.getName();
            LOG.info("latestLocalCommitHash="+latestLocalCommitHash);
            LOG.info("latestRemoteCommitHash="+latestRemoteCommitHash);

            return latestLocalCommitHash.equals(latestRemoteCommitHash);

        } catch (GitAPIException e) {
            throw new com.winchesters.devopsify.exception.git.GitAPIException(e);
        } catch (IOException e) {
            throw new GitException(e);
        }
    }

    @Override
    public Boolean localAndOriginMainInSync(GithubCredentials githubCredentials, String localRepoPath) throws GitException {
        return localAndRemoteInSync(githubCredentials,localRepoPath,"origin","main");
    }


    @Override
    public void pullOriginMain(GithubCredentials credentials, String path) throws GitException {
        pull(credentials, path, "origin", "main");
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
                    )
                    .setRebase(true);
            PullResult result = pull.call();
            if (!result.isSuccessful()) {
                LOG.error(String.format("Cannot pull from '%s', branch '%s'", remoteRepoName, remoteBranchName));
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
        } catch (IOException e) {
            throw new GitException(e);
        }
    }

    @Override
    public void clone(GithubCredentials credentials,String remoteUrl, String localPath) {
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

    @Override
    public void push(GithubCredentials credentials, String path, String remoteRepoName, String remoteBranchName) throws GitException {
        try {
            Repository repository = getRepository(path);
            Git git = new Git(repository);
            PushCommand push = git.push()
                    .setRemote(remoteRepoName)
                    .setRefSpecs(new RefSpec(remoteBranchName))
                    .setCredentialsProvider(
                            new UsernamePasswordCredentialsProvider(
                                    credentials.username(),
                                    credentials.personalAccessToken()
                            )
                    );

            RemoteRefUpdate remoteRefUpdate = push.call().iterator().next().getRemoteUpdates().iterator().next();
            LOG.info("Push was successful.");
            LOG.info("Response status : " + remoteRefUpdate.getStatus().name());

        } catch (GitAPIException e) {
            throw new com.winchesters.devopsify.exception.git.GitAPIException(e);
        } catch (IOException e) {
            throw new GitException(e);
        }
    }

    @Override
    public void pushOriginMain(GithubCredentials credentials, String path) throws GitException {
        push(credentials, path, "origin", "main");
    }

    @Override
    public void add(String path, String filePattern) {
        try {
            Repository repository = getRepository(path);
            Git git = new Git(repository);
            git.add()
                    .addFilepattern(filePattern)
                    .call();
            LOG.info("Add was successful.");

        } catch (IOException | NoFilepatternException e) {
            throw new GitException(e);
        } catch (GitAPIException e) {
            throw new com.winchesters.devopsify.exception.git.GitAPIException(e);
        }
    }

    @Override
    public void addAll(String path) {
        add(path, ".");
    }

    @Override
    public void commit(@NotNull String path,@NotNull String message) {
        try {
            Repository repository = getRepository(path);
            Git git = new Git(repository);
            RevCommit call = git.commit()
                    .setMessage(message)
                    .call();

            LOG.info("Commit was successful.");
            LOG.info(call.toString());

        } catch (IOException | NoFilepatternException e) {
            throw new GitException(e);
        } catch (GitAPIException e) {
            throw new com.winchesters.devopsify.exception.git.GitAPIException(e);
        }
    }

    @Override
    public void commitAll(String path,String message) {
        addAll(path);
        commit(path,message);
    }

    @Override
    public void syncLocalWithRemote(GithubCredentials githubCredentials, String path, String remoteName, String remoteBranchName) {
        pull(githubCredentials,path,remoteName,remoteBranchName);
        push(githubCredentials,path,remoteName,remoteBranchName);
    }

    @Override
    public void syncLocalWithOriginMain(GithubCredentials githubCredentials, String path) {
        syncLocalWithRemote(githubCredentials,path,"origin","main");
    }


}
