package com.winchesters.devopsify.service.technologies;

import com.winchesters.devopsify.enums.DockerFileType;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public interface GeneratedFile {

    void writeFile(String path) throws IOException;

    default void writeFile(String path, String fileName) throws IOException {
        File outputFile = new File(path + "/" + fileName);
        FileUtils.writeStringToFile(outputFile, this.getFileContent(), StandardCharsets.UTF_8);
    }

    

    /**
     * generates the content of a dockerfile from a template
     *
     * @return a string containing the dockerfile content
     * @throws IOException when io exception occurs
     */
    String getFileContent() throws IOException;

    /**
     * reads the template dockerfile
     *
     * @return the dockerfile template
     */
    File getFileTemplate();


}
