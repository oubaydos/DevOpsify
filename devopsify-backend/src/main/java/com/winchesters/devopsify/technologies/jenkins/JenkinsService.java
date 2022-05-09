package com.winchesters.devopsify.technologies.jenkins;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.user.ApiTokenData;
import com.winchesters.devopsify.exception.jenkins.JenkinsException;
import com.winchesters.devopsify.model.entity.Server;

import java.io.File;

public interface JenkinsService {

    void generateJenkinsFile(File directory);
    void pingJenkinsServer() throws JenkinsException;

    void setJenkinsClient(Server server);

    public JenkinsClient getJenkinsClient();

    public void installPlugins();

    public void saveGithubCredentials(String token);

    public void pullFromGithub();

    public void createGithubTrigger();

    public ApiTokenData createApiToken();

}
