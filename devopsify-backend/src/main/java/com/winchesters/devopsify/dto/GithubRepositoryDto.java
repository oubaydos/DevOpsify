package com.winchesters.devopsify.dto;


import org.kohsuke.github.GHRepository;

import javax.validation.constraints.NotNull;

/**
 * name
 * autoInit: Whether the repository is initialized with a minimal README
 * licenseTemplate: The license keyword of the open source license for this repository.
 * gitIgnoreTemplate: The desired language or platform to apply to the .gitignore.
 * owner: owner of the account
 * description: description
 * visibility: PUBLIC / PRIVATE
 * private_: boolean
 */
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
