package com.winchesters.devopsify.utils;

import com.winchesters.devopsify.model.entity.Server;

public class Utils {
    public static String addTrailingSlash(String website) {
        return website.endsWith("/") ? website : website + "/";
    }

    public static String toGithubRepositoryName(String name) {
        return name.trim().replace(" ", "-").replace("%20", "-");
    }
    public static boolean noFieldIsNullNorEmpty(Server server) {
        return server!=null &&
               server.username() != null &&
               server.password() != null &&
               server.url() != null ;
    }

}
