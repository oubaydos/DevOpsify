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

public class BackendDockerFile extends DockerFile {

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

    @Builder.Default
    private String  artifactName = ARTIFACT_NAME.defaultValue();


    @Override
    public String getDockerfileContent() throws IOException {
        File DockerfileTemplate = getDockerfileTemplate();
        DockerfileUtils dockerfileUtils = new DockerfileUtils(DockerfileTemplate);
        dockerfileUtils
                .setFileKeywordValue(
                        Map.of(
                                JDK_IMAGE_NAME.keyword(), jdkImageName,
                                BASE_BUILD_IMAGE_NAME.keyword(), baseBuildImageName,
                                BASE_BUILD_IMAGE_VERSION.keyword(), baseBuildImageVersion,
                                BASE_BUILD_JDK_TYPE.keyword(), baseBuildJdkType,


                                JDK_VERSION.keyword(), jdkVersion,
                                JDK_BASE_OS_NAME.keyword(), jdkBaseOsName,

                                WORKDIR.keyword(), workdir,
                                PORT.keyword(), port,

                                JAR_NAME.keyword(), jarName,
                                ARTIFACT_NAME.keyword(),artifactName
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
