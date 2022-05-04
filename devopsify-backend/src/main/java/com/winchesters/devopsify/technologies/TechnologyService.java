package com.winchesters.devopsify.technologies;

import java.io.File;
import java.io.IOException;

public interface TechnologyService {

    String scriptDirectory = System.getProperty("user.dir").concat("/scripts/");

    boolean installed();

    default boolean installed(String technology){
        try {
            new ProcessBuilder(technology, "--version").start();
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    void install();

    /**
     * @param scriptFilePath a relative path from scriptDirectory, it shouldn't start with '/'
     */

    default void installFromScript(String scriptFilePath){
        try {
            new ProcessBuilder("bash",scriptFilePath)
                    .inheritIO()
                    .directory(new File(scriptDirectory))
                    .start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
