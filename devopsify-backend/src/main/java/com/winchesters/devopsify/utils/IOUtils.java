package com.winchesters.devopsify.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOUtils {
    //TODO
    /***
     * this will cause issues because of anti-slash vs slash
     */
    private static String generatedFilesTemplatesBaseDirectory = System.getProperty("user.dir") + "/src/main/resources/project-templates/";
    private static String projectsDirectory = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString() + "\\devopsify";

    private static String dockerfileTemplatesBaseDirectory = generatedFilesTemplatesBaseDirectory + "dockerfile-templates/";
    private static String jenkinsfileTemplatesBaseDirectory = generatedFilesTemplatesBaseDirectory + "jenkins/";

    public static String InputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader stdInput
                = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder outputBuilder = new StringBuilder();
        String s;
        while ((s = stdInput.readLine()) != null) {
            outputBuilder.append(s);
        }
        return outputBuilder.toString();
    }

    public static String projectsDirectory() throws IOException {
        projectsDirectory = projectsDirectory.replace("\\", "/");
        Files.createDirectories(Paths.get(projectsDirectory));
        return projectsDirectory;
    }

    public static String dockerfileTemplatesBaseDirectory() {

        return dockerfileTemplatesBaseDirectory.replace("\\", "/");
    }

    public static String jenkinsfileTemplatesBaseDirectory() {
        return jenkinsfileTemplatesBaseDirectory.replace("\\", "/");
    }


}
