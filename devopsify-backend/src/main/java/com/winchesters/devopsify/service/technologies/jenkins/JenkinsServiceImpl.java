package com.winchesters.devopsify.service.technologies.jenkins;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.system.SystemInfo;
import com.cdancy.jenkins.rest.domain.user.ApiToken;
import com.cdancy.jenkins.rest.domain.user.ApiTokenData;
import com.winchesters.devopsify.exception.jenkins.JenkinsException;
import com.winchesters.devopsify.exception.jenkins.JenkinsServerException;
import com.winchesters.devopsify.model.JenkinsAnalyseResults;
import com.winchesters.devopsify.model.entity.Project;
import com.winchesters.devopsify.model.entity.Server;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class JenkinsServiceImpl implements JenkinsService {

    private static final Logger LOG = LoggerFactory.getLogger(JenkinsService.class);
    private final JenkinsClientFactory jenkinsClientFactory;

    private JenkinsClient jenkinsClient;
    public static void main(String[] args) {
        JenkinsService jenkinsService = new JenkinsServiceImpl(new JenkinsClientFactory());

        Server server = new Server(
                "http://188.166.100.241:8080/",
                "devopsify",
                "devopsify"
        );

        jenkinsService.setJenkinsClient(server);
        jenkinsService.pingJenkinsServer();
        LOG.debug(jenkinsService.createApiToken().tokenName());
        jenkinsService.createPipeline();
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
    public void pingJenkinsServer() throws JenkinsException{

        SystemInfo systemInfo = this.jenkinsClient.api().systemApi().systemInfo();
        LOG.info("pinging jenkins server...");
        systemInfo.errors().forEach(error -> {
            LOG.info("pinging jenkins server failed");
            throw new JenkinsServerException(error.exceptionName(),error.exceptionName());
        });
        LOG.info(String.format("jenkins version :%s",systemInfo.jenkinsVersion()));
    }
    public void installPlugins(){
        //TODO add all required plugins
        RequestStatus requestStatus  = this.jenkinsClient.api().pluginManagerApi().installNecessaryPlugins("maven-plugin");
        requestStatus.errors().forEach(error -> {
            LOG.info("installing plugin failed.");
            throw new JenkinsException(error.exceptionName(),error.message());
        });
    }

    public void setJenkinsClient(Server server){
        this.jenkinsClient = jenkinsClientFactory.getClient(server);
    }

    public JenkinsClient getJenkinsClient() {
        return jenkinsClient;
    }

    public ApiTokenData createApiToken(){
        ApiToken apiToken = this.jenkinsClient.api().userApi().generateNewToken("devOpsify");
        if(!apiToken.status().equals("ok")){
            throw new JenkinsException("JenkinsServiceImpl.createApiToken()", "Token generation failed");
        }
        return apiToken.data();
    }
    public void createPipeline(){
        //TODO
        // https://stackoverflow.com/questions/21405427/how-to-create-a-job-in-jenkins-by-using-simple-java-program
        String temp= """
                <?xml version='1.0' encoding='UTF-8'?>
                <project>
                  <actions/>
                  <description></description>
                  <keepDependencies>false</keepDependencies>
                  <properties/>
                  <scm class="hudson.scm.NullSCM"/>
                  <canRoam>true</canRoam>
                  <disabled>false</disabled>
                  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
                  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
                  <triggers/>
                  <concurrentBuild>false</concurrentBuild>
                  <builders/>
                  <publishers/>
                  <buildWrappers/>
                </project>""";
        LOG.info("{}",this.jenkinsClient.api().jobsApi().create(null,"tmp",temp).toString());
    }

    @Override
    public JenkinsAnalyseResults analyseJenkins(Project project) {
        //TODO
        return new JenkinsAnalyseResults();
    }

    public void saveGithubCredentials(String token){
    }
    public void pullFromGithub(){
    }
    public void createGithubTrigger(){

    }
}
