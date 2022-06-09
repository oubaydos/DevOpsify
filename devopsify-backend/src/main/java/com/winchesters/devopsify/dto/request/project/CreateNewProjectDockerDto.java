package com.winchesters.devopsify.dto.request.project;

import com.winchesters.devopsify.dto.request.BackendDockerfileDto;
import com.winchesters.devopsify.dto.request.DataBaseDockerfileDto;

public record CreateNewProjectDockerDto(
        Boolean dockerizeBackend,
        Boolean defaultDockerBackend,
        BackendDockerfileDto dockerBackend,
        Boolean dockerizeDB,
        DataBaseDockerfileDto dockerDB,
        Boolean defaultDockerDB
        ) {
}
