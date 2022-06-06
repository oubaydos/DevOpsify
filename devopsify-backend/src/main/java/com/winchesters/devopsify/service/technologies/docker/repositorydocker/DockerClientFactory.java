package com.winchesters.devopsify.service.technologies.docker.repositorydocker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.winchesters.devopsify.service.technologies.docker.dockerfile.DataBaseDockerFile;

import java.time.Duration;

import static com.winchesters.devopsify.service.technologies.docker.repositorydocker.DockerImage.buildImageFromDockerFileInBaseDirectory;

public class DockerClientFactory {
    private static DockerClient dockerClient;
    private static final String DOCKER_HOST_URL = "tcp://localhost:2375";

    private DockerClientFactory() {

    }

    public static DockerClient getInstance() {
        if (dockerClient == null) {
            dockerClient = DockerClientImpl.getInstance(getDockerClientConfig(), getDockerHttpClient());
        }
        return dockerClient;
    }


    private static DockerClientConfig getDockerClientConfig() {
        // registry is not set
        // in order to connect, you have got to first expose docker through DOCKER_HOST_URL
        return DefaultDockerClientConfig
                .createDefaultConfigBuilder()
                .withDockerHost(DOCKER_HOST_URL)
                .withDockerTlsVerify(false)
                .build();
    }

    private static DockerHttpClient getDockerHttpClient() {
        // registry is not set
        // in order to connect, you have got to first expose docker through DOCKER_HOST_URL
        DockerClientConfig dockerClientConfig = getDockerClientConfig();
        return new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost())
                .sslConfig(dockerClientConfig.getSSLConfig())
                .maxConnections(100)
                .connectionTimeout(Duration.ofSeconds(30))
                .responseTimeout(Duration.ofSeconds(45))
                .build();
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(
//                buildImageFromDockerFileInBaseDirectory(
//                        "C:/Users/oubay/OneDrive/Bureau/projects/DevOpsify/devopsify-backend/",
//                        "tempname:tag"
//                )
//        );
        DataBaseDockerFile dataBaseDockerFile = new DataBaseDockerFile("image", null, null, null);
        System.out.println(dataBaseDockerFile.getDockerfileContent());
    }


}
