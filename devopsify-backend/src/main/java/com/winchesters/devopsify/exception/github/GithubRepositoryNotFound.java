package com.winchesters.devopsify.exception.github;

import com.winchesters.devopsify.exception.GeneralException;

public class GithubRepositoryNotFound extends GeneralException {
    public GithubRepositoryNotFound() {
        super(GithubRepositoryNotFound.class.getSimpleName(),"the github repository is not found");
    }
}
