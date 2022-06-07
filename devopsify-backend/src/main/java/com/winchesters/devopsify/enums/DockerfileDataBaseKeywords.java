package com.winchesters.devopsify.enums;

public enum DockerfileDataBaseKeywords {
    DATABASE_NAME("db-name"),
    DATABASE_VERSION("db-version"),
    BASE_OS("db-os"),
    DATABASE_INIT_QUERIES_FILENAME("db-init-queries-filename");

    private final String value;
    public final String value() { return this.value; }
    DockerfileDataBaseKeywords(String value) {
        this.value = value;
    }
}
