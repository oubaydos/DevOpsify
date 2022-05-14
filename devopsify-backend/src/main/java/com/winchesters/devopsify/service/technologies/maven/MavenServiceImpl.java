package com.winchesters.devopsify.service.technologies.maven;

public class MavenServiceImpl implements MavenService{

    @Override
    public boolean installed() {
        return installed("maven");
    }

    @Override
    public void install() {
        installFromScript("maven/maven_install.sh");
    }
}
