package com.winchesters.devopsify.technologies.jenkins;

import java.io.File;

public interface JenkinsService {
    void generateJenkinsFile(File directory);
    void pingJenkinsServer(String serverUrl);
}
