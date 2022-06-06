package com.winchesters.devopsify.dto.request;

public record GenerateMavenProjectDto(
        String archetypeArtifactId,
        String groupId,
        String artifactId,
        String version
) {
}
