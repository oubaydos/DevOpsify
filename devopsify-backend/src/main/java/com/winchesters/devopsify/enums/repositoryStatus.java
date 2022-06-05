package com.winchesters.devopsify.enums;

public enum repositoryStatus {
    OKAY("OKAY"),
    LICENSE_MISSING("LICENSE MISSING"),
    README_PROBLEM("README PROBLEM"),
    GITIGNORE_MISSING("GITIGNORE MISSING");

    private final String value;
    repositoryStatus(String value) {
        this.value = value;
    }
}
