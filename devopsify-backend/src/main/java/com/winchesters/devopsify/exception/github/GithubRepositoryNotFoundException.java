package com.winchesters.devopsify.exception.github;

import com.winchesters.devopsify.exception.GeneralException;

public class GithubRepositoryNotFoundException extends GeneralException {
    public GithubRepositoryNotFoundException() {
        super(GithubRepositoryNotFoundException.class.getSimpleName(),"the github repository is not found");
    }
}
