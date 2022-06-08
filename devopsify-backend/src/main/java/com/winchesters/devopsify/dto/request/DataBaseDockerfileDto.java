package com.winchesters.devopsify.dto.request;

public record DataBaseDockerfileDto(
        String imageName,
        String imageVersion,
        String imageBaseOS,
        String dbInitQueriesFilename
) {
}
