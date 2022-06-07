package com.winchesters.devopsify.dto.request.project;

import javax.validation.constraints.NotNull;

public record CreateNewProjectGeneralDto(

        @NotNull String name,

        String description
) {
}
