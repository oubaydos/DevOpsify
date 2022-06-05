package com.winchesters.devopsify.service.technologies.docker.repositorydocker;


import com.winchesters.devopsify.service.technologies.github.branch.Branch;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

import static com.winchesters.devopsify.service.technologies.github.branch.Branch.DEFAULT_BRANCH_NAME;


@RequiredArgsConstructor
@AllArgsConstructor
public class DockerRepositoryAnalyser {
    private final GHRepository repository;
    private String branchName = DEFAULT_BRANCH_NAME;

    public int numberOfDockerFiles() throws IOException {
        Branch branch = new Branch(branchName,repository);
        return (int) branch
                .listFilePaths()
                .stream()
                .filter(
                        s -> s.toLowerCase().matches(".*Dockerfile")
                )
                .count();
    }
}
