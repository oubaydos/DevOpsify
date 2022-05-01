package com.winchesters.devopsify.technologies;

public interface TechnologyService {

    boolean installed();
    Version getVersion();
}
