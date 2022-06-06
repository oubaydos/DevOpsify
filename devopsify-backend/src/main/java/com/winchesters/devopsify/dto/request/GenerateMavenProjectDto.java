package com.winchesters.devopsify.dto.request;

public record GenerateMavenProjectDto(
        String archetypeArtifactId,
        String archetypeGroupId,
        String groupId,
        String artifactId,
        String version
) {
}
