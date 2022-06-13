package com.winchesters.devopsify.enums;

public enum DockerfileBackEndKeywords implements FileKeywords {

    BASE_BUILD_IMAGE_NAME("base-image-name", "maven"),
    BASE_BUILD_IMAGE_VERSION("image-version", "3.8.5"),
    BASE_BUILD_JDK_TYPE("jdk-type", "openjdk"),

    ARTIFACT_NAME("artifact-name","example"),

    JDK_IMAGE_NAME("jdk-image-name", "openjdk"),
    JDK_VERSION("jdk-version", "17"),
    JDK_BASE_OS_NAME("jdk-base-os-name", "alpine"),

    WORKDIR("workdir", "workdir"),
    PORT("exposed-port", "8080"),

    JAR_NAME("output-jar-name", "output");

    private final String keyword;
    private final String defaultValue;

    @Override
    public String defaultValue() {
        return defaultValue;
    }

    @Override
    public final String keyword() {
        return this.keyword;
    }

    DockerfileBackEndKeywords(String keyword, String defaultValue) {
        this.keyword = keyword;
        this.defaultValue = defaultValue;
    }


}
