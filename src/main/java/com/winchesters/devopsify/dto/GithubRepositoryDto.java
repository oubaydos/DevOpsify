package com.winchesters.devopsify.dto;


import org.kohsuke.github.GHRepository;

import javax.validation.constraints.NotNull;

public record GithubRepositoryDto(@NotNull String name,
                                  Boolean autoInit,
                                  String licenseTemplate,
                                  String gitIgnoreTemplate,
                                  String owner,
                                  String description,
                                  GHRepository.Visibility visibility,
                                  Boolean private_
                                  ) {
}
