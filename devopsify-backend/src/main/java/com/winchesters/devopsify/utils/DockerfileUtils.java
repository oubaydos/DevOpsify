package com.winchesters.devopsify.utils;

import com.winchesters.devopsify.dto.request.BackendDockerfileDto;
import com.winchesters.devopsify.dto.request.DataBaseDockerfileDto;
import com.winchesters.devopsify.service.technologies.docker.dockerfile.BackendDockerFile;
import com.winchesters.devopsify.service.technologies.docker.dockerfile.DataBaseDockerFile;
import com.winchesters.devopsify.service.technologies.docker.dockerfile.DockerFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;

public class DockerfileUtils extends FilesUtils {

    public DockerfileUtils(File file) throws IOException {
        super(file, "#");
    }

    public static String commentLine(File file, int lineNumber) throws IOException {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        lineNumber--;
        lines.set(lineNumber, "#" + lines.get(lineNumber));
        return StringUtils.join(lines, "\n");
    }

    public String getDockerfileContent() {
        return getFileContent();
    }

    public static BackendDockerFile backendDockerfileDtoToBackendDockerFile(BackendDockerfileDto dto, Boolean defaultDockerBackend) {
        if (defaultDockerBackend) {
            return BackendDockerFile.builder().build();
        }
        return BackendDockerFile.builder()
                .setBaseBuildImageName(dto.baseBuildImageName())
                .setBaseBuildImageVersion(dto.baseBuildImageVersion())
                .setBaseBuildJdkType(dto.baseBuildJdkType())
                .setBuildOnly(dto.buildOnly())
                .setJarName(dto.jarName())
                .setJdkBaseOsName(dto.jdkBaseOsName())
                .setJdkImageName(dto.jdkImageName())
                .setJdkVersion(dto.jdkVersion())
                .setPort(dto.port())
                .setWorkdir(dto.workdir())
                .build();
    }

    public static DockerFile dataBaseDockerfileDtoToDataBaseDockerFile(DataBaseDockerfileDto dto, Boolean defaultDockerDB) {
        if (defaultDockerDB) {
            return DataBaseDockerFile.builder().build();
        }
        return DataBaseDockerFile.builder()
                .setImageBaseOS(dto.imageBaseOS())
                .setDbInitQueriesFilename(dto.dbInitQueriesFilename())
                .setImageName(dto.imageName())
                .setImageVersion(dto.imageVersion())
                .build();
    }

}
