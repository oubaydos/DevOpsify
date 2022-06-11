package com.winchesters.devopsify.service.technologies.docker.repositorydocker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.winchesters.devopsify.service.technologies.jenkins.JenkinsFile;

import java.time.Duration;

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
//        DockerFile dockerFile = DataBaseDockerFile
//                .builder()
//                .setImageBaseOS("ubuntu")
//                .setImageVersion("v5")
//                .setImageName("mysql")
//                .setDbInitQueriesFilename("hiu")
//                .build();
//        System.out.println(dockerFile.getDockerfileContent());
//        dockerFile.writeFile("./Dockerfile-helloworld");
        /*
         * backend dockerfile test
         */
//        BackendDockerFile.BackendDockerFileBuilder backendDockerFile = BackendDockerFile.builder();
//
//        backendDockerFile.setBaseBuildImageName("buildImage");
//        backendDockerFile.setBaseBuildImageVersion("v1");
//        backendDockerFile.setBaseBuildJdkType("oracleJdk");
//
//        backendDockerFile.setJdkImageName("oracleJdk");
//        backendDockerFile.setJdkBaseOsName("ubuntu");
//        backendDockerFile.setJdkVersion("16");
//
//        backendDockerFile.setWorkdir("project");
//        backendDockerFile.setPort("700");
//
//        backendDockerFile.setJarName("HelloJar");
//
//        backendDockerFile.setBuildOnly(true);
//        System.out.println(backendDockerFile.build().getDockerfileContent());
        /*
         * frontend dockerfile test
         */
//        FrontEndDockerFile backendDockerFile = new FrontEndDockerFile();
//
//        backendDockerFile.setNodeVersion("v11");
//        backendDockerFile.setWorkdir("workdir-devopsify");
//        backendDockerFile.setMiniCssExtractPluginVersion("v5");
//
//        backendDockerFile.setNginxVersion("v6");
//        backendDockerFile.setNginxBaseOs("ubuntu");
//        backendDockerFile.setNginxConfigurationFileLocation("/home/nginx/nginx.cong");
//        backendDockerFile.setProductionPort("900");
//
//        backendDockerFile.setBuildOnly(true);
//        backendDockerFile.setHasNginxConfigurationFile(false);
//
//        System.out.println(backendDockerFile.getDockerfileContent());

        // jenkins
        JenkinsFile jenkinsFile = JenkinsFile
                .builder()
                .setEc2Ip("192.168.1.1")
                .setDockerhubUsername("oubaydos")
                .setGithubRepositoryUrl("github.com/oubaydos/devopsify")
                .setEc2Username("ubuntu")
                .setImageName("temp-image")
                .setEc2ContainerPort("7070")
                .setEc2DeploymentPort("3000")
                .setWithDeployment(false)
                .build();


        System.out.println(jenkinsFile.getGroovyScriptContent());
        jenkinsFile.writeFile("./hello-world");
    }


}
