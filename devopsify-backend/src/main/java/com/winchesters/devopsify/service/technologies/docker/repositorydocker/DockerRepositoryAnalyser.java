package com.winchesters.devopsify.service.technologies.docker.repositorydocker;


import com.winchesters.devopsify.service.technologies.github.branch.Branch;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.kohsuke.github.GHRepository;

import java.io.IOException;

import static com.winchesters.devopsify.service.technologies.github.branch.Branch.DEFAULT_BRANCH_NAME;



public class DockerRepositoryAnalyser {
    private final GHRepository repository;
    private String branchName = DEFAULT_BRANCH_NAME;
    private final Branch branch;

    public DockerRepositoryAnalyser(GHRepository repository) {
        this.repository = repository;
        this.branch = new Branch(branchName,repository);
    }

    public DockerRepositoryAnalyser(GHRepository repository, String branchName) {
        this.repository = repository;
        this.branchName = branchName;
        this.branch = new Branch(branchName,repository);
    }

    public int numberOfDockerFiles() throws IOException {
        return branch.numberOfFile("Dockerfile");
    }
    public int numberOfDockerComposeFiles() throws IOException {
        return branch.numberOfFile("docker-compose.yml") + branch.numberOfFile("docker-compose.yaml");
    }
}
