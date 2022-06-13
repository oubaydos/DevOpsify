package com.winchesters.devopsify.utils;

public class UrlUtils {
    public static String addTrailingSlash(String website) {
        return website.endsWith("/") ? website : website + "/";
    }

}
