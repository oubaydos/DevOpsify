package com.winchesters.devopsify.enums;

public enum DockerfileDataBaseKeywords {
    DATABASE_NAME("db-name", "postgres"),
    DATABASE_VERSION("db-version", "14"),
    BASE_OS("db-os", "alpine"),
    DATABASE_INIT_QUERIES_FILENAME("db-init-queries-filename", null);

    private final String keyword;
    private final String defaultValue;

    public String defaultValue() {
        return defaultValue;
    }

    public final String keyword() {
        return this.keyword;
    }

    DockerfileDataBaseKeywords(String keyword, String defaultValue) {
        this.keyword = keyword;
        this.defaultValue = defaultValue;
    }
}
