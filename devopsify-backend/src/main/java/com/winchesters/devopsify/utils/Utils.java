package com.winchesters.devopsify.utils;

import java.lang.reflect.Field;

public class Utils {
    public static String addTrailingSlash(String website) {
        return website.endsWith("/") ? website : website + "/";
    }

    public static String toGithubRepositoryName(String name) {
        return name.trim().replace(" ", "-").replace("%20", "-");
    }
    public static boolean noFieldIsNullNorEmpty(Object object) throws IllegalAccessException {
        for (Field f : object.getClass().getDeclaredFields())
            if (f.get(object) == null || String.valueOf(f.get(object)).isEmpty())
                return false;
        return true;
    }

}
