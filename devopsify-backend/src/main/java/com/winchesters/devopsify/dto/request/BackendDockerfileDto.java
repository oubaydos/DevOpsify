package com.winchesters.devopsify.dto.request;

public record BackendDockerfileDto(
        String baseBuildImageName,
        String baseBuildImageVersion,
        String baseBuildJdkType,
        String jdkImageName,
        String jdkVersion,
        String jdkBaseOsName,
        String workdir,
        String port,
        String jarName,
        Boolean buildOnly
) {
}
