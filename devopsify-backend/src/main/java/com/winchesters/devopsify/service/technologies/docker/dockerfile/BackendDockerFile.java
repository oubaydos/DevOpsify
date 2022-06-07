package com.winchesters.devopsify.service.technologies.docker.dockerfile;

import com.winchesters.devopsify.enums.DockerFileType;
import com.winchesters.devopsify.utils.DockerfileUtils;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.winchesters.devopsify.enums.DockerfileBackEndKeywords.*;

@NoArgsConstructor
public class BackendDockerFile implements DockerFileFactory {

    private String baseBuildImageName = BASE_BUILD_IMAGE_NAME.defaultValue();
    private String baseBuildImageVersion = BASE_BUILD_IMAGE_VERSION.defaultValue();
    private String baseBuildJdkType = BASE_BUILD_JDK_TYPE.defaultValue();

    private String jdkImageName = JDK_IMAGE_NAME.defaultValue();
    private String jdkVersion = JDK_VERSION.defaultValue();
    private String jdkBaseOsName = JDK_BASE_OS_NAME.defaultValue();

    private String workdir = WORKDIR.defaultValue();
    private String port = PORT.defaultValue();

    private String jarName = JAR_NAME.defaultValue();

    private boolean buildOnly = false;


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

    public void setBaseBuildImageName(String baseBuildImageName) {
        this.baseBuildImageName = baseBuildImageName;
    }

    public void setBaseBuildImageVersion(String baseBuildImageVersion) {
        this.baseBuildImageVersion = baseBuildImageVersion;
    }

    public void setBaseBuildJdkType(String baseBuildJdkType) {
        this.baseBuildJdkType = baseBuildJdkType;
    }

    public void setJdkImageName(String jdkImageName) {
        this.jdkImageName = jdkImageName;
    }

    public void setJdkVersion(String jdkVersion) {
        this.jdkVersion = jdkVersion;
    }

    public void setJdkBaseOsName(String jdkBaseOsName) {
        this.jdkBaseOsName = jdkBaseOsName;
    }

    public void setWorkdir(String workdir) {
        this.workdir = workdir;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public void setBuildOnly(boolean buildOnly) {
        this.buildOnly = buildOnly;
    }
}
