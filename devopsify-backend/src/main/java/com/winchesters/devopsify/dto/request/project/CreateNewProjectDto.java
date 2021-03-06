package com.winchesters.devopsify.dto.request.project;

public record CreateNewProjectDto (
    String name,
    String location,
    String language,
    String buildSystem,
    String JDK,
    Boolean initGitRepository
){}