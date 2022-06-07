package com.winchesters.devopsify.service.technologies.docker.dockerfile;

import com.winchesters.devopsify.enums.DockerFileType;
import com.winchesters.devopsify.utils.DockerfileUtils;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.winchesters.devopsify.enums.DockerfileDataBaseKeywords.*;

@NoArgsConstructor
public class DataBaseDockerFile implements DockerFileFactory {
    private String imageName = DATABASE_NAME.defaultValue();
    private String imageVersion = DATABASE_VERSION.defaultValue();
    private String imageBaseOS = BASE_OS.defaultValue();
    private String dbInitQueriesFilename;



    @Override
    public void writeDockerfile() {
        //TODO
    }

    @Override
    public String getDockerfileContent() throws IOException {
        File DockerfileTemplate = getDockerfileTemplate();
        DockerfileUtils dockerfileUtils = new DockerfileUtils(DockerfileTemplate);
        dockerfileUtils
                .setDockerfileKeywordValue(
                        Map.of(
                                DATABASE_NAME.keyword(), imageName,
                                DATABASE_VERSION.keyword(), imageVersion,
                                BASE_OS.keyword(), imageBaseOS
                        )
                );
        if (dbInitQueriesFilename == null)
            dockerfileUtils.commentLine(2);
        else
            dockerfileUtils
                    .setDockerfileKeywordValue(
                            DATABASE_INIT_QUERIES_FILENAME.keyword(),
                            dbInitQueriesFilename
                    );
        return dockerfileUtils.getDockerfileContent();
    }

    @Override
    public File getDockerfileTemplate() {
        return getDockerfileTemplate(DockerFileType.DATABASE);
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setImageVersion(String imageVersion) {
        this.imageVersion = imageVersion;
    }

    public void setImageBaseOS(String imageBaseOS) {
        this.imageBaseOS = imageBaseOS;
    }

    public void setDbInitQueriesFilename(String dbInitQueriesFilename) {
        this.dbInitQueriesFilename = dbInitQueriesFilename;
    }
}
