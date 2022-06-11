package com.winchesters.devopsify.service.technologies.docker.dockerfile;

import com.winchesters.devopsify.enums.DockerFileType;
import com.winchesters.devopsify.utils.DockerfileUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.winchesters.devopsify.enums.DockerfileDataBaseKeywords.*;

@Builder(setterPrefix = "set")
@AllArgsConstructor
public class DataBaseDockerFile extends DockerFile {
    @Builder.Default
    private String imageName = DATABASE_NAME.defaultValue();
    @Builder.Default
    private String imageVersion = DATABASE_VERSION.defaultValue();
    @Builder.Default
    private String imageBaseOS = BASE_OS.defaultValue();
    private String dbInitQueriesFilename;


    @Override
    public String getDockerfileContent() throws IOException {
        File DockerfileTemplate = getDockerfileTemplate();
        DockerfileUtils dockerfileUtils = new DockerfileUtils(DockerfileTemplate);
        dockerfileUtils
                .setFileKeywordValue(
                        Map.of(
                                DATABASE_NAME.keyword(), imageName,
                                DATABASE_VERSION.keyword(), imageVersion,
                                BASE_OS.keyword(), imageBaseOS
                        )
                );
        if (dbInitQueriesFilename == null){
            int dbInitQueriesStatementLineNumber = dockerfileUtils.getLineContaining(DATABASE_INIT_QUERIES_FILENAME.keyword()).get(0);
            dockerfileUtils.commentLine(dbInitQueriesStatementLineNumber);
        }

        else
            dockerfileUtils
                    .setFileKeywordValue(
                            DATABASE_INIT_QUERIES_FILENAME.keyword(),
                            dbInitQueriesFilename
                    );
        return dockerfileUtils.getDockerfileContent();
    }

    @Override
    public File getDockerfileTemplate() {
        return getDockerfileTemplate(DockerFileType.DATABASE);
    }

}
