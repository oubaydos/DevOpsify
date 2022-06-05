package com.winchesters.devopsify.exception.github;

import com.winchesters.devopsify.exception.GeneralException;

public class GithubRepositoryBranchNotFoundException extends GeneralException {
    public GithubRepositoryBranchNotFoundException() {
        super(GithubRepositoryBranchNotFoundException.class.getSimpleName(), "the repository branch is not found");
    }

    public GithubRepositoryBranchNotFoundException(String branchName) {
        super(GithubRepositoryBranchNotFoundException.class.getSimpleName(), "the repository branch " + branchName + " is not found");
    }
}