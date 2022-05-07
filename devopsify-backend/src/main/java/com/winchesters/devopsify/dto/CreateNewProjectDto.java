package com.winchesters.devopsify.dto;

public record CreateNewProjectDto (
    String name,
    String location,
    String language,
    String buildSystem,
    String JDK,
    boolean initGitRepository
){}