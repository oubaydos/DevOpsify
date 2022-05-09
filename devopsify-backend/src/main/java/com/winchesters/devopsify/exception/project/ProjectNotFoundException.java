package com.winchesters.devopsify.exception.project;

import com.winchesters.devopsify.exception.GeneralException;

public class ProjectNotFoundException extends GeneralException {
    public ProjectNotFoundException() {
        super("ProjectNotFoundException", "project not found");
    }
}
