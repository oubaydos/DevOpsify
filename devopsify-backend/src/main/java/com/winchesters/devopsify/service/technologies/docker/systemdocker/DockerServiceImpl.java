package com.winchesters.devopsify.service.technologies.docker.systemdocker;

import com.winchesters.devopsify.dto.request.BackendDockerfileDto;
import com.winchesters.devopsify.dto.request.DataBaseDockerfileDto;
import com.winchesters.devopsify.service.technologies.docker.dockerfile.BackendDockerFile;
import com.winchesters.devopsify.service.technologies.docker.dockerfile.DockerFile;
import com.winchesters.devopsify.utils.DockerfileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.winchesters.devopsify.enums.DockerfileBackEndKeywords.ARTIFACT_NAME;

@Service
@RequiredArgsConstructor
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
    public void generateDockerfile(DockerFile dockerfile, String path) throws IOException {
        dockerfile.writeFile(path);
    }

    @Override
    public void install() {
        installDocker();
        installDockerCompose();
    }

    @Override
    public byte[] viewDataBaseDockerfile(DataBaseDockerfileDto dto) throws IOException {
        DockerFile dockerFile = DockerfileUtils.dataBaseDockerfileDtoToDataBaseDockerFile(dto,false);
        return dockerFile.getDockerfileContent().getBytes();
    }

    @Override
    public byte[] viewBackendDockerfile(BackendDockerfileDto dto) throws IOException {
        BackendDockerFile backendDockerFile = DockerfileUtils.backendDockerfileDtoToBackendDockerFile(dto,false,ARTIFACT_NAME.defaultValue());
        return backendDockerFile.getDockerfileContent().getBytes();
    }


}
