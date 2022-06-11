package com.winchesters.devopsify.service.technologies.docker.dockerfile;

import com.winchesters.devopsify.enums.DockerFileType;
import com.winchesters.devopsify.service.technologies.GeneratedFile;

import java.io.File;
import java.io.IOException;

import static com.winchesters.devopsify.utils.IOUtils.dockerfileTemplatesBaseDirectory;

public abstract class DockerFile implements GeneratedFile {

    /**
     * generates the dockerfile
     *
     * @param path full path -- filename included
     */
    @Override
    public final void writeFile(String path) throws IOException {
        writeFile(path, "Dockerfile");
    }


    /**
     * generates the content of a dockerfile from a template
     *
     * @return a string containing the dockerfile content
     * @throws IOException when io exception occurs
     */
    public abstract String getDockerfileContent() throws IOException;

    @Override
    public String getFileContent() throws IOException {
        return getDockerfileContent();
    }

    /**
     * reads the template dockerfile
     *
     * @return the dockerfile template
     */
    protected abstract File getDockerfileTemplate();

    @Override
    public File getFileTemplate() {
        return getDockerfileTemplate();
    }

    /**
     * @param dockerFileType the type of the template -- db - maven - react ..
     *                       reads the template dockerfile
     * @return the dockerfile template
     */
    protected final File getDockerfileTemplate(DockerFileType dockerFileType) {
        return new File(dockerfileTemplatesBaseDirectory() + "DockerfileTemplate-" + dockerFileType.value());
    }
}
