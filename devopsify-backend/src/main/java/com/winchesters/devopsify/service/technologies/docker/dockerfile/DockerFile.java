package com.winchesters.devopsify.service.technologies.docker.dockerfile;

import com.winchesters.devopsify.enums.DockerFileType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.winchesters.devopsify.utils.IOUtils.dockerfileTemplatesBaseDirectory;

public abstract class DockerFile {

    /**
     * generates the dockerfile
     * @param path full path -- filename included
     */
    public final void writeDockerfile(String path) throws IOException {
        String fileName ="Dockerfile";
        File outputFile = new File(path+"/"+fileName);
        FileUtils.writeStringToFile(outputFile, this.getDockerfileContent(), StandardCharsets.UTF_8);
    }

    /**
     * generates the content of a dockerfile from a template
     * @return a string containing the dockerfile content
     * @throws IOException when io exception occurs
     */
    public abstract String getDockerfileContent() throws IOException;

    /**
     * reads the template dockerfile
     * @return the dockerfile template
     */
    protected abstract File getDockerfileTemplate();

    /**
     * @param dockerFileType the type of the template -- db - maven - react ..
     * reads the template dockerfile
     * @return the dockerfile template
     */
    protected final File getDockerfileTemplate(DockerFileType dockerFileType) {
        return new File(dockerfileTemplatesBaseDirectory() + "DockerfileTemplate-" + dockerFileType.value());
    }
}
