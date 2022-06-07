package com.winchesters.devopsify.service.technologies.docker.dockerfile;

import com.winchesters.devopsify.enums.DockerFileType;

import java.io.File;
import java.io.IOException;

import static com.winchesters.devopsify.utils.IOUtils.dockerfileTemplatesBaseDirectory;

public interface DockerFileFactory {
    String DEFAULT_IMAGE_NAME = "SCRATCH";
    String DEFAULT_IMAGE_VERSION = "latest";
    String DEFAULT_IMAGE_BASE_OS = "alpine";

    void writeDockerfile();

    String getDockerfileContent() throws IOException;
    File getDockerfileTemplate();

    default File getDockerfileTemplate(DockerFileType dockerFileType) {
        return new File(dockerfileTemplatesBaseDirectory() + "DockerfileTemplate-" + dockerFileType.value());
    }
}
