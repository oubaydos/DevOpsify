package com.winchesters.devopsify.enums;

public enum DockerfileFrontEndKeywords implements FileKeywords {


    // BUILD
    NODE_VERSION("node-version","latest"),
    WORKDIR("workdir", "workdir"),
    MINI_CSS_EXTRACT_PLUGIN_VERSION("mini-css-extract-plugin-version","2.4.5"),

    // PRODUCTION
    NGINX_VERSION("nginx-version","latest"),
    NGINX_BASE_OS("nginx-base-os","alpine"),
    NGINX_CONFIGURATION_FILE_LOCATION("nginx-configuration-file-location","nginx/nginx.conf"),
    PRODUCTION_PORT("production-port","80");

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

    DockerfileFrontEndKeywords(String keyword, String defaultValue) {
        this.keyword = keyword;
        this.defaultValue = defaultValue;
    }
}
