package com.winchesters.devopsify.service.technologies.docker.dockerfile;

import com.winchesters.devopsify.enums.DockerFileType;
import com.winchesters.devopsify.utils.DockerfileUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.winchesters.devopsify.enums.DockerfileBackEndKeywords.*;

@Builder(setterPrefix = "set")
@AllArgsConstructor

public class BackendDockerFile implements DockerFileFactory {

    @Builder.Default
    private String baseBuildImageName = BASE_BUILD_IMAGE_NAME.defaultValue();
    @Builder.Default
    private String baseBuildImageVersion = BASE_BUILD_IMAGE_VERSION.defaultValue();
    @Builder.Default
    private String baseBuildJdkType = BASE_BUILD_JDK_TYPE.defaultValue();

    @Builder.Default
    private String jdkImageName = JDK_IMAGE_NAME.defaultValue();
    @Builder.Default
    private String jdkVersion = JDK_VERSION.defaultValue();
    @Builder.Default
    private String jdkBaseOsName = JDK_BASE_OS_NAME.defaultValue();

    @Builder.Default
    private String workdir = WORKDIR.defaultValue();
    @Builder.Default
    private String port = PORT.defaultValue();

    @Builder.Default
    private String jarName = JAR_NAME.defaultValue();

    @Builder.Default
    private Boolean buildOnly = false;


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
                                BASE_BUILD_IMAGE_NAME.keyword(), baseBuildImageName,
                                BASE_BUILD_IMAGE_VERSION.keyword(), baseBuildImageVersion,
                                BASE_BUILD_JDK_TYPE.keyword(), baseBuildJdkType,

                                JDK_IMAGE_NAME.keyword(), jdkImageName,
                                JDK_VERSION.keyword(), jdkVersion,
                                JDK_BASE_OS_NAME.keyword(), jdkBaseOsName,

                                WORKDIR.keyword(), workdir,
                                PORT.keyword(), port,

                                JAR_NAME.keyword(), jarName
                        )
                );
        if (buildOnly) {
            // exception
            int secondPartStartLine = dockerfileUtils.getLineContaining("FROM").get(1);
            dockerfileUtils.commentAllLinesAfter(secondPartStartLine);
        }
        return dockerfileUtils.getDockerfileContent();
    }

    @Override
    public File getDockerfileTemplate() {
        return getDockerfileTemplate(DockerFileType.BACKEND);
    }

}
