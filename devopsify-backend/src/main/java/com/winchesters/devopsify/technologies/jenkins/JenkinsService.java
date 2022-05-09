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

    JenkinsClient getJenkinsClient();

    void installPlugins();

    void saveGithubCredentials(String token);

    void pullFromGithub();

    void createGithubTrigger();

    ApiTokenData createApiToken();
    void createPipeline();

}
