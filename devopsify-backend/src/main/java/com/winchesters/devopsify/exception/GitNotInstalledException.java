package com.winchesters.devopsify.exception;

public class GitNotInstalledException extends GitException{
    public GitNotInstalledException() {
        super("GitNotInstalledException", "git is not installed");
    }
}
