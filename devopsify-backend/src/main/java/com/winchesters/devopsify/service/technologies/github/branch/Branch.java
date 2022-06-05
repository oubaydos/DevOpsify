package com.winchesters.devopsify.service.technologies.github.branch;

import com.winchesters.devopsify.exception.github.GithubRepositoryBranchNotFoundException;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHTreeEntry;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Branch {
    private static final String DEFAULT_BRANCH_NAME = "main";
    private static final String GIT_IGNORE = ".gitignore";
    private GHRepository repository;

    public Branch(GHRepository repository) {
        this.repository = repository;
    }

    public List<String> listFilePaths() throws IOException {
        return this.listFilePaths(DEFAULT_BRANCH_NAME);
    }

    public List<String> listFilePaths(String branchName) throws IOException {
        GHBranch branch = Optional.ofNullable(repository.getBranch(branchName))
                .orElseThrow(() -> new GithubRepositoryBranchNotFoundException(branchName));
        List<GHTreeEntry> tree = repository.getTreeRecursive(branch.getSHA1(), 1).getTree();
        return tree.stream().map(GHTreeEntry::getPath).toList();
    }

    public boolean containsFile(String filename) throws IOException {
        return listFilePaths()
                .stream()
                .anyMatch(
                        s -> s.toLowerCase().contains(filename.toLowerCase())
                );
    }

    public boolean containsGitIgnore() throws IOException {
        return this.containsFile(GIT_IGNORE);
    }
}
