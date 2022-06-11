package com.winchesters.devopsify.service.technologies.jenkins;

import com.winchesters.devopsify.service.technologies.GeneratedFile;
import com.winchesters.devopsify.utils.JenkinsfileUtils;
import lombok.Builder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.winchesters.devopsify.enums.JenkinsfileGroovyScriptKeywords.*;
import static com.winchesters.devopsify.utils.IOUtils.jenkinsfileTemplatesBaseDirectory;

@Builder(setterPrefix = "set")
public class JenkinsFile implements GeneratedFile {
    @Builder.Default
    private String imageName = IMAGE_NAME.defaultValue();
    @Builder.Default
    private String dockerhubUsername = DOCKERHUB_USERNAME.defaultValue();
    @Builder.Default
    private String ec2Username = EC2_USERNAME.defaultValue();
    @Builder.Default
    private String ec2Ip = EC2_IP.defaultValue();
    @Builder.Default
    private String ec2ContainerPort = EC2_CONTAINER_PORT.defaultValue();
    @Builder.Default
    private String ec2DeploymentPort = EC2_DEPLOYMENT_PORT.defaultValue();
    @Builder.Default
    private String githubRepositoryUrl = GITHUB_REPOSITORY_URL.defaultValue();
    @Builder.Default
    private Boolean withDeployment = true;

    /**
     * writes both Jenkinsfile and script.groovy
     *
     * @param path the path of the files
     * @throws IOException when io exception
     */
    @Override
    public final void writeFile(String path) throws IOException {
        writeFile(path, "Jenkinsfile");
        File outputFile = new File(path + "/script.groovy");
        FileUtils.writeStringToFile(outputFile, this.getGroovyScriptContent(), StandardCharsets.UTF_8);
    }

    @Override
    public String getFileContent() throws IOException {
        JenkinsfileUtils jenkinsfileUtils = new JenkinsfileUtils(getFileTemplate());
        if (!withDeployment) {
            for (int i : jenkinsfileUtils.getLineContaining("gv.deployImage()")) {
                jenkinsfileUtils.commentLine(i);
            }
        }
        return jenkinsfileUtils.getFileContent();
    }

    public String getGroovyScriptContent() throws IOException {
        File groovyScriptFile = getGroovyScriptTemplate();
        JenkinsfileUtils groovyScriptUtils = new JenkinsfileUtils(groovyScriptFile);
        groovyScriptUtils
                .setFileKeywordValue(
                        Map.of(
                                IMAGE_NAME.keyword(), imageName,
                                DOCKERHUB_USERNAME.keyword(), dockerhubUsername,
                                EC2_USERNAME.keyword(), ec2Username,
                                EC2_IP.keyword(), ec2Ip,
                                EC2_CONTAINER_PORT.keyword(), ec2ContainerPort,
                                EC2_DEPLOYMENT_PORT.keyword(), ec2DeploymentPort,
                                GITHUB_REPOSITORY_URL.keyword(), githubRepositoryUrl
                        )
                );
        return groovyScriptUtils.getFileContent();
    }

    @Override
    public File getFileTemplate() {
        return new File(jenkinsfileTemplatesBaseDirectory() + "Jenkinsfile");
    }

    public File getGroovyScriptTemplate() {
        return new File(jenkinsfileTemplatesBaseDirectory() + "script.groovy");
    }
}
