package com.winchesters.devopsify.dto.request.project;

public record CreateNewProjectGithubDto(
        Boolean autoInit,
        String licenseTemplate,
        String gitIgnoreTemplate,
        Boolean private_
) {

}
