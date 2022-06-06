package com.winchesters.devopsify.service.technologies.docker.repositorydocker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.dockerfile.Dockerfile;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.apache.commons.io.FileUtils;

import javax.print.Doc;
import java.io.File;
import java.time.Duration;
import java.util.Set;

import static org.hamcrest.Matchers.emptyString;

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
                .connectionTimeout(Duration.ofSeconds(2))
                .responseTimeout(Duration.ofSeconds(2))
                .build();
    }



}
