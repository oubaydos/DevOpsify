package com.winchesters.devopsify.enums;

public enum RepositoryStatus {
    OKAY("OKAY"),
    LICENSE_MISSING("LICENSE MISSING"),
    README_PROBLEM("README PROBLEM"),
    GITIGNORE_MISSING("GITIGNORE MISSING");

    private final String value;
    RepositoryStatus(String value) {
        this.value = value;
    }
}
