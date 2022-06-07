package com.winchesters.devopsify.service.technologies.docker.dockerfile;

import com.winchesters.devopsify.enums.DockerFileType;
import com.winchesters.devopsify.utils.DockerfileUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.winchesters.devopsify.enums.DockerfileFrontEndKeywords.*;

@Builder(setterPrefix = "set")
@AllArgsConstructor
public class FrontEndDockerFile implements DockerFileFactory {
    // BUILD
    @Builder.Default
    String nodeVersion = NODE_VERSION.defaultValue();
    @Builder.Default
    String workdir = WORKDIR.defaultValue();
    @Builder.Default
    String miniCssExtractPluginVersion = MINI_CSS_EXTRACT_PLUGIN_VERSION.defaultValue();

    // PRODUCTION
    @Builder.Default
    String nginxVersion = NGINX_VERSION.defaultValue();
    @Builder.Default
    String nginxBaseOs = NGINX_BASE_OS.defaultValue();
    @Builder.Default
    String nginxConfigurationFileLocation = NGINX_CONFIGURATION_FILE_LOCATION.defaultValue();
    @Builder.Default
    String productionPort = PRODUCTION_PORT.defaultValue();
    @Builder.Default
    private boolean buildOnly = false;
    @Builder.Default
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

}
