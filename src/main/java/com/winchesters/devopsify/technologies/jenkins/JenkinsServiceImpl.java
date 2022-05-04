package com.winchesters.devopsify.technologies.jenkins;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;
import com.winchesters.devopsify.exception.JenkinsException;
import com.winchesters.devopsify.technologies.git.GitServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JenkinsServiceImpl implements JenkinsService {

    private final Logger LOG = LoggerFactory.getLogger(GitServiceImpl.class);
    public static void main(String[] args) {
        JenkinsService jenkinsService = new JenkinsServiceImpl();
        String jenkinsUrl = "http://188.166.100.241:8080/";
        jenkinsService.pingJenkinsServer(jenkinsUrl,"benyazidhamza","DoBh8E@?m5Mr4PeB");

    }

    @Override
    public void generateJenkinsFile(File directory) {
        //for now, it only generates empty Jenkinsfile
        try {
            File file = new File(directory, "Jenkinsfile");
            FileWriter writer = new FileWriter(file);
            writer.append("//empty Jenkinsfile");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pingJenkinsServer(String serverUrl,String username,String password) throws JenkinsException{
        JenkinsClient client = JenkinsClient.builder()
                .endPoint(serverUrl) // Optional. Defaults to http://127.0.0.1:8080
                .credentials(String.format("%s:%s",username,password)) // Optional.
                .build();

        SystemInfo systemInfo = client.api().systemApi().systemInfo();
        LOG.info("pinging jenkins server...");
        systemInfo.errors().forEach(error -> {
            LOG.info("pinging jenkins server failed");
            throw new JenkinsException(error.exceptionName(),error.exceptionName());
        });
        LOG.info(String.format("jenkins version :%s",systemInfo.jenkinsVersion()));
    }
}
