package com.winchesters.devopsify.dto;


import javax.validation.constraints.NotNull;

public record GithubRepositoryDto(@NotNull String name,
                                  Boolean autoInit,
                                  String licenseTemplate,
                                  String gitIgnoreTemplate,
                                  String owner) {
}
