package com.winchesters.devopsify.dto;

public record CreateNewProjectDto (
    String name,
    String location,
    boolean initGitRepository
){}