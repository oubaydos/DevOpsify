package com.winchesters.devopsify.technologies.docker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DockerServiceImpl implements DockerService {

    public static void main(String[] args){
//        DockerService dockerService = new DockerServiceImpl();
    }

    @Override
    public boolean dockerInstalled() {
        return installed("docker");
    }

    @Override
    public boolean dockerComposeInstalled() {
        return installed("docker-compose");
    }

    @Override
    public boolean installed() {
        return this.dockerComposeInstalled() && this.dockerInstalled();
    }


    @Override
    public void generateDockerFile(File directory) {
        //for now, it only generates empty Dockerfile
        try{
            File file = new File(directory,"Dockerfile");
            FileWriter writer = new FileWriter(file);
            writer.append("#empty Dockerfile");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void installDockerCompose() {
        this.installFromScript("docker/docker_compose_install.sh");

    }

    @Override
    public void installDocker() {
        this.installFromScript("docker/docker_install.sh");
    }

    @Override
    public void install() {
        installDocker();
        installDockerCompose();
    }


}
