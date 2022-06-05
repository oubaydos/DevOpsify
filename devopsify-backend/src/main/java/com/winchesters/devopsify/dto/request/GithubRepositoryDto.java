package com.winchesters.devopsify.dto.request;


import com.winchesters.devopsify.dto.request.CreateNewProjectWithInitDto;
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
    public GithubRepositoryDto(@NotNull String name, Boolean autoInit, String licenseTemplate, String gitIgnoreTemplate, String owner, String description, GHRepository.Visibility visibility, Boolean private_) {
        this.name = name;
        this.autoInit = autoInit;
        this.licenseTemplate = licenseTemplate;
        this.gitIgnoreTemplate = gitIgnoreTemplate;
        this.owner = owner;
        this.description = description;
        this.visibility = visibility;
        this.private_ = private_;
    }
    public GithubRepositoryDto(CreateNewProjectWithInitDto newProjectWithInitDto) {
        this(
                newProjectWithInitDto.name(),
                newProjectWithInitDto.autoInit(),
                newProjectWithInitDto.licenseTemplate(),
                newProjectWithInitDto.gitIgnoreTemplate(),
                null,
                newProjectWithInitDto.description(),
                null,
                newProjectWithInitDto.private_()
        );
    }
}
