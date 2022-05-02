package com.winchesters.devopsify.technologies;

import java.io.IOException;

public interface TechnologyService {

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
}
