package com.winchesters.devopsify.dto;

import javax.validation.constraints.NotNull;

public record CreateNewProjectWithInitDto(
        @NotNull String name,
        Boolean autoInit,
        String licenseTemplate,
        String gitIgnoreTemplate,
        String description,
        Boolean private_
) {
}
