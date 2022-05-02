package com.winchesters.devopsify.technologies.maven;

public class MavenServiceImpl implements MavenService{

    @Override
    public boolean installed() {
        return installed("maven");
    }
}
