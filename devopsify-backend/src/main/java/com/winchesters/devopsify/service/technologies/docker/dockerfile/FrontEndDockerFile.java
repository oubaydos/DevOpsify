package com.winchesters.devopsify.service.technologies.docker.dockerfile;

import com.winchesters.devopsify.enums.DockerFileType;
import com.winchesters.devopsify.utils.DockerfileUtils;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.winchesters.devopsify.enums.DockerfileFrontEndKeywords.*;

@NoArgsConstructor
public class FrontEndDockerFile implements DockerFileFactory {
    // BUILD
    String nodeVersion = NODE_VERSION.defaultValue();
    String workdir = WORKDIR.defaultValue();
    String miniCssExtractPluginVersion = MINI_CSS_EXTRACT_PLUGIN_VERSION.defaultValue();

    // PRODUCTION
    String nginxVersion = NGINX_VERSION.defaultValue();
    String nginxBaseOs = NGINX_BASE_OS.defaultValue();
    String nginxConfigurationFileLocation = NGINX_CONFIGURATION_FILE_LOCATION.defaultValue();
    String productionPort = PRODUCTION_PORT.defaultValue();
    private boolean buildOnly = false;
    private boolean hasNginxConfigurationFile = false;

    @Override
    public void writeDockerfile() {

    }

    @Override
    public String getDockerfileContent() throws IOException {
        File DockerfileTemplate = getDockerfileTemplate();
        DockerfileUtils dockerfileUtils = new DockerfileUtils(DockerfileTemplate);
        dockerfileUtils
                .setDockerfileKeywordValue(
                        Map.of(
                                NODE_VERSION.keyword(), nodeVersion,
                                WORKDIR.keyword(), workdir,
                                MINI_CSS_EXTRACT_PLUGIN_VERSION.keyword(), miniCssExtractPluginVersion,

                                NGINX_VERSION.keyword(), nginxVersion,
                                NGINX_BASE_OS.keyword(), nginxBaseOs,
                                NGINX_CONFIGURATION_FILE_LOCATION.keyword(), nginxConfigurationFileLocation,
                                PRODUCTION_PORT.keyword(), productionPort

                        )
                );
        if (buildOnly) {
            // exception
            int secondPartStartLine = dockerfileUtils.getLineContaining("FROM").get(1);
            dockerfileUtils.commentAllLinesAfter(secondPartStartLine);
        } else if (hasNginxConfigurationFile) {
            int nginxConfLine = dockerfileUtils.getLineContaining(nginxConfigurationFileLocation).get(0);
            dockerfileUtils.unCommentLine(nginxConfLine);
        } else {
            // TODO, must copy the default nginx conf we have to the project
        }
        return dockerfileUtils.getDockerfileContent();
    }

    @Override
    public File getDockerfileTemplate() {
        return getDockerfileTemplate(DockerFileType.FRONTEND);
    }

    public void setNodeVersion(String nodeVersion) {
        this.nodeVersion = nodeVersion;
    }

    public void setWorkdir(String workdir) {
        this.workdir = workdir;
    }

    public void setMiniCssExtractPluginVersion(String miniCssExtractPluginVersion) {
        this.miniCssExtractPluginVersion = miniCssExtractPluginVersion;
    }

    public void setNginxVersion(String nginxVersion) {
        this.nginxVersion = nginxVersion;
    }

    public void setNginxBaseOs(String nginxBaseOs) {
        this.nginxBaseOs = nginxBaseOs;
    }

    public void setNginxConfigurationFileLocation(String nginxConfigurationFileLocation) {
        this.nginxConfigurationFileLocation = nginxConfigurationFileLocation;
    }

    public void setProductionPort(String productionPort) {
        this.productionPort = productionPort;
    }

    public void setBuildOnly(boolean buildOnly) {
        this.buildOnly = buildOnly;
    }

    public void setHasNginxConfigurationFile(boolean hasNginxConfigurationFile) {
        this.hasNginxConfigurationFile = hasNginxConfigurationFile;
    }
}
