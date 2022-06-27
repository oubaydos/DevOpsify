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


    /**
     * gets the byte array the database's dockerfile
     * @param dataBaseDockerfileDto a dto containing all parameters of the dockerfile
     * @return byte array of the dockerfile
     * @throws IOException if the is I/O problems
     */
    @PostMapping("db")
    public byte[] viewDataBaseDockerfile(@ModelAttribute DataBaseDockerfileDto dataBaseDockerfileDto) throws IOException {
        return dockerService.viewDataBaseDockerfile(dataBaseDockerfileDto);
    }


    /**
     * gets the byte array the database's dockerfile
     * @param backendDockerfileDto a dto containing all parameters of the dockerfile
     * @return byte array of the dockerfile
     * @throws IOException if the is I/O problems
     */@PostMapping("backend")
    public byte[] viewBackendDockerfile(@ModelAttribute BackendDockerfileDto backendDockerfileDto) throws IOException {
        return dockerService.viewBackendDockerfile(backendDockerfileDto);
    }

    /**
     * @return default values used for generating the dockerfiles
     */
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
