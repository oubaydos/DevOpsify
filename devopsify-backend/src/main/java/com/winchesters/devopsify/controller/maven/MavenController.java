package com.winchesters.devopsify.controller.maven;


import com.winchesters.devopsify.dto.response.MavenArchetypeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/maven")
public class MavenController {

    @GetMapping("/archetype")
    List<MavenArchetypeDto> getArchetypes() {
        return List.of(
                new MavenArchetypeDto(
                        "org.apache.maven.archetypes",
                        "maven-archetype-quickstart",
                        "An archetype which contains a sample Maven project"
                ),
                new MavenArchetypeDto(
                        "org.springframework.boot",
                        "spring-boot-sample-data-jpa-archetype",
                        "Spring Boot Data JPA Sample"
                ),
                new MavenArchetypeDto(
                        "nl.delphinity",
                        "springboot",
                        "A basic starter template using springboot, jpa data, thymeleaf and MVC"
                ),
                new MavenArchetypeDto(
                        "jp.blackawa",
                        "spring-bootstrapping-archetype",
                        "Bootstrap Spring Boot project with Spring Security Database Authentication/Authorization"
                )
        );
    }
}
