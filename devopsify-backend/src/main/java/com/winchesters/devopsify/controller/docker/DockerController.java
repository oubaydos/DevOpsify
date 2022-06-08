package com.winchesters.devopsify.controller.docker;


import com.winchesters.devopsify.dto.request.BackendDockerfileDto;
import com.winchesters.devopsify.dto.request.DataBaseDockerfileDto;
import com.winchesters.devopsify.dto.response.DockerfileDefaultValuesDto;
import com.winchesters.devopsify.enums.DockerfileBackEndKeywords;
import com.winchesters.devopsify.enums.DockerfileDataBaseKeywords;
import com.winchesters.devopsify.service.technologies.docker.systemdocker.DockerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "api/v1/docker")
@RequiredArgsConstructor
public class DockerController {

    private final DockerService dockerService;


    @PostMapping("db")
    public byte[] viewDataBaseDockerfile(@ModelAttribute DataBaseDockerfileDto dto) throws IOException {
        return dockerService.viewDataBaseDockerfile(dto);
    }

    @PostMapping("backend")
    public byte[] viewBackendDockerfile(@ModelAttribute BackendDockerfileDto dto) throws IOException {
        return dockerService.viewBackendDockerfile(dto);
    }

    @GetMapping("default-values")
    public DockerfileDefaultValuesDto getDockerfileDefaultValues() {

        return new DockerfileDefaultValuesDto(
                Stream.of(DockerfileBackEndKeywords.values())
                        .collect(Collectors
                                .toMap(DockerfileBackEndKeywords::keyword,
                                        DockerfileBackEndKeywords::defaultValue)),
                Stream.of(DockerfileDataBaseKeywords.values())
                .collect(Collectors
                        .toMap(DockerfileDataBaseKeywords::keyword,
                                DockerfileDataBaseKeywords::defaultValue))
        );
    }

}
