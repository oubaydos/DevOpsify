package com.winchesters.devopsify.service.technologies.docker.dockerfile;

import com.winchesters.devopsify.enums.DockerFileType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import static com.winchesters.devopsify.utils.IOUtils.dockerfileTemplatesBaseDirectory;

public abstract class DockerFile {

    /**
     * @param path full path -- filename included
     */
    public final void writeDockerfile(String path) throws IOException {
        File outputFile = new File(path);
        FileUtils.writeStringToFile(outputFile, this.getDockerfileContent(), StandardCharsets.UTF_8);
    }

    abstract String getDockerfileContent() throws IOException;

    abstract File getDockerfileTemplate();

    final File getDockerfileTemplate(DockerFileType dockerFileType) {
        return new File(dockerfileTemplatesBaseDirectory() + "DockerfileTemplate-" + dockerFileType.value());
    }
}
