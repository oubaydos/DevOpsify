package com.winchesters.devopsify.enums;

public enum JenkinsfileGroovyScriptKeywords implements FileKeywords {

    IMAGE_NAME("image-name", "temp"),
    ARTIFACT_NAME("artifact-name","example"),
    DOCKERHUB_USERNAME("dockerhub-username", "devopsify"),
    EC2_USERNAME("ec2-username", "ec2-user"),
    EC2_IP("ec2-ip", "0.0.0.0"),
    EC2_CONTAINER_PORT("ec2-container-port", "8080"),
    EC2_DEPLOYMENT_PORT("ec2-deployment-port", "8080"),
    GITHUB_REPOSITORY_URL("github-repository-url", "github.com/devopsify/temp");

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

    JenkinsfileGroovyScriptKeywords(String keyword, String defaultValue) {
        this.keyword = keyword;
        this.defaultValue = defaultValue;
    }
}
