package com.winchesters.devopsify.exception.github;

public class GithubNotContainingLoginException extends GithubException {
    public GithubNotContainingLoginException() {
        super("github does not contain login/username, you should send it explicitly");
    }
}
