package com.winchesters.devopsify.service.technologies.git;

import com.winchesters.devopsify.exception.git.GitException;
import com.winchesters.devopsify.exception.git.GitNotInstalledException;
import com.winchesters.devopsify.model.GithubCredentials;
import com.winchesters.devopsify.service.technologies.TechnologyService;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

public interface GitService extends TechnologyService {


    /**
     * initialize a git repository in the given path
     *
     * @param path a string representing the path of a directory
     * @throws GitNotInstalledException if git is not installed in your system
     */
    void initializeRepository(String path) throws GitNotInstalledException;

    /**
     * pull from origin main
     *
     * @param credentials an object containing credentials to access remote repository
     * @param path        the path where the pull with be executed
     * @throws GitException if there was a problem connecting to the remote repository ,
     * repository not initialized in the path or git not installed
     */
    void pullOriginMain(GithubCredentials credentials, String path) throws GitException;

    /**
     * pull
     * @param credentials an object containing credentials to access remote repository
     * @param path        the path where the pull with be executed

     * @param remoteRepoName the name of remote repository . you can find the name by executing
     *                       "git remote -v"
     * @param remoteBranchName the name of remote branch
     * @throws GitException if there was a problem connecting to the remote repository ,
     * repository not initialized in the path or git not installed
     */
    void pull(GithubCredentials credentials, String path, String remoteRepoName, String remoteBranchName) throws GitException;

    /**
     *
     * @param credentials an object containing credentials to access remote repository
     * @param remoteUrl url of the remote repository to clone
     * @param localPath path where the repository will be cloned
     * @throws IOException if there is I/O problems
     * @throws GitAPIException if there is problem connecting to the remote repository
     */
    void clone(GithubCredentials credentials, String remoteUrl, String localPath) throws IOException, GitAPIException;

    /**
     * get the Repository instance of a git repository in the given directory
     * @param path path of a git repository
     * @return Repository instance representing the git repository
     * @throws IOException if there is I/O problems
     */
    Repository getRepository(String path) throws IOException;

    /**
     * push to a remote repository
     * @param credentials an object containing credentials to access remote repository
     * @param path path of a git repository
     * @param remoteRepoName the name of remote repository . you can find the name by executing
     *                       "git remote -v"
     * @param remoteBranchName the name of remote branch
     * @throws GitAPIException if there is problem connecting to the remote repository
     */
    void push(GithubCredentials credentials, String path, String remoteRepoName, String remoteBranchName) throws GitAPIException, IOException;

    /**
     * push to origin main
     * @param credentials an object containing credentials to access remote repository
     * @param path path of a git repository
     * @throws GitAPIException if there is problem connecting to the remote repository
     */
    void pushOriginMain(GithubCredentials credentials, String path) throws GitException, GitAPIException, IOException;

    /**
     * stage changes
     * @param path path of a git repository
     * @param filePattern pattern of files to stage
     */
    void add(String path, String filePattern);

    /**
     * stage all changes
     * @param path path of a git repository
     */
    void addAll(String path);

    /**
     * commit changes
     * @param path path of a git repository
     * @param message message of the commit
     */
    void commit(String path, String message);

    /**
     * commit all changes
     * @param path path of a git repository
     * @param message message of the commit
     */
    void commitAll(String path, String message);

    /**
     * checks if a local repository and a remote repository are in sync
     * @param githubCredentials an object containing credentials to access remote repository
     * @param localRepoPath path of the local repository
     * @param remoteRepoName the name of remote repository . you can find the name by executing
     *                       "git remote -v"
     * @param remoteBranchName the name of remote branch
     * @return true they are in sync , false otherwise
     * @throws IOException if there is I/O problems
     * @throws GitAPIException if there is problem connecting to the remote repository
     */
    Boolean localAndRemoteInSync(GithubCredentials githubCredentials, String localRepoPath, String remoteRepoName, String remoteBranchName) throws IOException, GitAPIException;


    /**
     * checks if a local repository and origin main are in sync
     * @param githubCredentials an object containing credentials to access remote repository
     * @param localRepoPath path of the local repository
     * @return true they are in sync , false otherwise
     * @throws IOException if there is I/O problems
     * @throws GitAPIException if there is problem connecting to the remote repository
     */
    Boolean localAndOriginMainInSync(GithubCredentials githubCredentials, String localRepoPath) throws GitAPIException, IOException;


    /**
     * sync a local repository with a remote repository
     * @param githubCredentials an object containing credentials to access remote repository
     * @param path path of the local repository
     * @param remoteRepoName the name of remote repository . you can find the name by executing
     *                       "git remote -v"
     * @param remoteBranchName the name of remote branch
     * @throws IOException if there is I/O problems
     * @throws GitAPIException if there is problem connecting to the remote repository
     */
    void syncLocalWithRemote(GithubCredentials githubCredentials, String path, String remoteRepoName, String remoteBranchName) throws GitAPIException, IOException;

    /**
     * sync a local repository with origin main
     * @param githubCredentials an object containing credentials to access remote repository
     * @param path path of the local repository
     * @throws IOException if there is I/O problems
     * @throws GitAPIException if there is problem connecting to the remote repository
     */
    void syncLocalWithOriginMain(GithubCredentials githubCredentials, String path) throws GitAPIException, IOException;


}
