package com.winchesters.devopsify.dto.response;

public record MavenArchetypeDto(
        String groupId,
        String artifactId,
        String description
) {
}
