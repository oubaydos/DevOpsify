package com.winchesters.devopsify.service.technologies;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

public interface TechnologyService {

    String scriptDirectory = System.getProperty("user.dir").concat("/scripts/");
    String homeDirectory = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString();
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
