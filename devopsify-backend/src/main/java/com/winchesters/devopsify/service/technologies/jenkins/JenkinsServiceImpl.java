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
import java.util.List;

@RequiredArgsConstructor
@Service
public class JenkinsServiceImpl implements JenkinsService {

    private static final Logger LOG = LoggerFactory.getLogger(JenkinsService.class);
    private static final List<String> requiredPlugins = List.of("ldap@2.10", "credentials@1126.ve05618c41e62", "pipeline-github-lib@38.v445716ea_edda_", "authentication-tokens@1.4", "github-oauth@0.38", "scm-api@608.vfa_f971c5a_a_e9", "okhttp-api@4.9.3-105.vb96869f8ac3a", "javax-mail-api@1.6.2-6", "multibranch-scan-webhook-trigger@1.0.9", "token-macro@293.v283932a_0a_b_49", "workflow-job@1182.v60a_e6279b_579", "ignore-committer-strategy@1.0.4", "variant@1.4", "github-branch-source@1637.vd833b_7ca_7654", "caffeine-api@2.9.3-65.v6a_47d0f4d1fe", "github-api@1.303-400.v35c2d8258028", "momentjs@1.1.1", "matrix-project@771.v574584b_39e60", "pipeline-groovy-lib@591.v3a_7f422b_d058", "workflow-support@820.vd1a_6cc65ef33", "gradle@1.38", "jquery@1.12.4-1", "workflow-aggregator@581.v0c46fa_697ffd", "matrix-auth@3.1.2", "display-url-api@2.3.6", "pipeline-milestone-step@101.vd572fef9d926", "javax-activation-api@1.2.0-3", "ant@475.vf34069fef73c", "github@1.34.3", "ssh@2.6.1", "workflow-multibranch@716.vc692a_e52371b_", "workflow-api@1153.vb_912c0e47fb_a_", "resource-disposer@0.19", "ace-editor@1.1", "branch-api@2.1046.v0ca_37783ecc5", "plugin-util-api@2.17.0", "pipeline-model-api@2.2086.v12b_420f036e5", "jquery3-api@3.6.0-4", "pipeline-stage-view@2.24", "email-ext@2.88", "apache-httpcomponents-client-4-api@4.5.13-1.0", "pipeline-graph-analysis@195.v5812d95a_a_2f9", "font-awesome-api@6.1.1-1", "workflow-durable-task-step@1139.v252a_e12e8463", "credentials-binding@523.vd859a_4b_122e6", "workflow-scm-step@400.v6b_89a_1317c9a_", "handlebars@3.0.8", "bootstrap5-api@5.1.3-7", "workflow-basic-steps@948.v2c72a_091b_b_68", "echarts-api@5.3.2-2", "bouncycastle-api@2.26", "snakeyaml-api@1.30.1", "structs@318.va_f3ccb_729b_71", "pipeline-model-definition@2.2086.v12b_420f036e5", "git-client@3.11.0", "workflow-step-api@625.vd896b_f445a_f8", "pipeline-rest-api@2.24", "ssh-agent@295.v9ca_a_1c7cc3a_a_", "jaxb@2.3.6-1", "script-security@1175.v4b_d517d6db_f0", "jsch@0.1.55.2", "sshd@3.237.v883d165a_c1d3", "antisamy-markup-formatter@2.7", "command-launcher@84.v4a_97f2027398", "junit@1.63", "cloudbees-folder@6.722.v8165b_a_cf25e9", "git-parameter@0.9.16", "ssh-slaves@1.814.vc82988f54b_10", "jdk-tool@1.5", "pipeline-utility-steps@2.12.1", "durable-task@496.va67c6f9eefa7", "pam-auth@1.8", "pipeline-build-step@2.18", "pipeline-stage-tags-metadata@2.2086.v12b_420f036e5", "popper2-api@2.11.5-2", "git@4.11.3", "ssh-credentials@277.v95c2fec1c047", "basic-branch-build-strategies@1.3.2", "ws-cleanup@0.42", "build-timeout@1.21", "checks-api@1.7.4", "plain-credentials@1.8", "trilead-api@1.57.v6e90e07157e1", "pipeline-stage-step@293.v200037eefcd5", "jjwt-api@0.11.2-71.v2722b_b_06a_2a_f", "pipeline-input-step@448.v37cea_9a_10a_70", "timestamper@1.17", "workflow-cps@2706.v71dd22b_c5a_a_2", "jackson2-api@2.13.3-285.vc03c0256d517", "pipeline-model-extensions@2.2086.v12b_420f036e5", "mailer@414.vcc4c33714601");
    private final JenkinsClientFactory jenkinsClientFactory;

    private JenkinsClient jenkinsClient;

    public static void main(String[] args) {
        JenkinsServiceImpl jenkinsService = new JenkinsServiceImpl(new JenkinsClientFactory());

        Server server = new Server(
                "http://3.86.242.247:7070/",
                "devopsify",
                "devopsify"
        );

        jenkinsService.setJenkinsClient(server);
        jenkinsService
                .installRequiredPlugins();
        System.out.println(jenkinsService.getJenkinsClient().api().pluginManagerApi().plugins(1,null).plugins().size());

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
    public void pingJenkinsServer() throws JenkinsException {

        SystemInfo systemInfo = this.jenkinsClient.api().systemApi().systemInfo();
        LOG.info("pinging jenkins server...");
        systemInfo.errors().forEach(error -> {
            LOG.info("pinging jenkins server failed");
            throw new JenkinsServerException(error.exceptionName(), error.exceptionName());
        });
        LOG.info(String.format("jenkins version :%s", systemInfo.jenkinsVersion()));
    }

    @Override
    public void pingJenkinsServer(Server server) throws JenkinsException {
        JenkinsClient client = jenkinsClientFactory.getClient(server);
        SystemInfo systemInfo = client.api().systemApi().systemInfo();
        LOG.info("pinging jenkins server : " + server);
        systemInfo.errors().forEach(error -> {
            LOG.info("pinging jenkins server failed");
            throw new JenkinsServerException(error.exceptionName(), error.exceptionName());
        });
        LOG.info(String.format("jenkins version :%s", systemInfo.jenkinsVersion()));
    }

    @Override
    public void installRequiredPlugins() {
        requiredPlugins.forEach(this::installPlugin);
    }

    /**
     * @param pluginId name@version
     */
    private void installPlugin(String pluginId) {
        RequestStatus requestStatus = this.jenkinsClient.api().pluginManagerApi().installNecessaryPlugins(pluginId);
        requestStatus.errors().forEach(error -> {
            LOG.info("installing plugin " + pluginId + " failed.");
            throw new JenkinsException(error.exceptionName(), error.message());
        });
        LOG.info(pluginId + " plugin is installed");
    }

    public void setJenkinsClient(Server server) {
        this.jenkinsClient = jenkinsClientFactory.getClient(server);
    }

    public JenkinsClient getJenkinsClient() {
        return jenkinsClient;
    }

    public ApiTokenData createApiToken() {
        ApiToken apiToken = this.jenkinsClient.api().userApi().generateNewToken("devOpsify");
        if (!apiToken.status().equals("ok")) {
            throw new JenkinsException("JenkinsServiceImpl.createApiToken()", "Token generation failed");
        }
        return apiToken.data();
    }

    public void createPipeline() {
        //TODO
        // https://stackoverflow.com/questions/21405427/how-to-create-a-job-in-jenkins-by-using-simple-java-program
        String temp = """
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
        LOG.info("{}", this.jenkinsClient.api().jobsApi().create(null, "tmp", temp).toString());
    }

    @Override
    public JenkinsAnalyseResults analyseJenkins(Project project) {
        //TODO
        return new JenkinsAnalyseResults();
    }

    public void saveGithubCredentials(String token) {
    }

    public void pullFromGithub() {
    }

    public void createGithubTrigger() {

    }
}
