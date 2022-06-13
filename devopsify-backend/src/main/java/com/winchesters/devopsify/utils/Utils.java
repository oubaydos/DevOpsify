package com.winchesters.devopsify.utils;

public class Utils {
    public static String addTrailingSlash(String website) {
        return website.endsWith("/") ? website : website + "/";
    }

    public static String toGithubRepositoryName(String name) {
        return name.replace(" ", "-").replace("%20", "-");
    }

}
