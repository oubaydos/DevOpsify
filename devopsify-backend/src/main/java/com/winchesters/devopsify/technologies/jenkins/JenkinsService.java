package com.winchesters.devopsify.technologies.jenkins;

import com.winchesters.devopsify.exception.JenkinsException;

import java.io.File;

public interface JenkinsService {
    void generateJenkinsFile(File directory);
    void pingJenkinsServer(String serverUrl,String username,String password) throws JenkinsException;
}
