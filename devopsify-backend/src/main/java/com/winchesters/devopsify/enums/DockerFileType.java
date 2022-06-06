package com.winchesters.devopsify.enums;

public enum DockerFileType {
    DATABASE("database"),
    BACKEND("backend"),
    FRONTEND("frontend"),
    EMPTY("empty");
    private String value;

    DockerFileType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
