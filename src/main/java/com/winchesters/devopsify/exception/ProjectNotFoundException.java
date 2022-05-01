package com.winchesters.devopsify.exception;

public class ProjectNotFoundException extends GeneralException{
    public ProjectNotFoundException() {
        super("ProjectNotFoundException", "project not found");
    }
}
