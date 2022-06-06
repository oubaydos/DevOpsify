package com.winchesters.devopsify.service.technologies.docker.repositorydocker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.core.util.CompressArchiveUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class DockerImage {
    public static String buildImageFromDockerFileInBaseDirectory(String baseDirectoryPath, String imageName) throws Exception {
        File baseDir = new File(baseDirectoryPath);
        Collection<File> files = FileUtils.listFiles(baseDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        File tarFile = CompressArchiveUtil.archiveTARFiles(baseDir, files, UUID.randomUUID().toString());
        return dockerfileBuild(new FileInputStream(tarFile), imageName);

    }

    protected static String dockerfileBuild(InputStream tarInputStream, String imageName) throws Exception {
        DockerClient dockerClient = DockerClientFactory.getInstance();
        BuildImageCmd buildImageCmd = dockerClient
                .buildImageCmd()
                .withTarInputStream(tarInputStream)
                .withTags(Set.of(imageName));
        return execBuild(buildImageCmd);
    }

    private static String execBuild(BuildImageCmd buildImageCmd) {
        DockerClient dockerClient = DockerClientFactory.getInstance();
        String imageId = buildImageCmd.start().awaitImageId();
        CreateContainerResponse container = dockerClient.createContainerCmd(imageId).exec();
        dockerClient.startContainerCmd(container.getId()).exec();
        dockerClient.waitContainerCmd(container.getId()).start().awaitStatusCode();
        return imageId;
    }
}
