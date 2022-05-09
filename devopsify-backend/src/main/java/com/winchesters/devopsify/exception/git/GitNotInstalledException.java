package com.winchesters.devopsify.exception.git;

public class GitNotInstalledException extends GitException{
    public GitNotInstalledException() {
        super("GitNotInstalledException", "git is not installed");
    }
}
