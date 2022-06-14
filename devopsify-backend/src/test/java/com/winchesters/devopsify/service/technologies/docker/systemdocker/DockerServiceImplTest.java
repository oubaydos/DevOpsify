package com.winchesters.devopsify.service.technologies.docker.systemdocker;

import com.winchesters.devopsify.dto.request.BackendDockerfileDto;
import com.winchesters.devopsify.dto.request.DataBaseDockerfileDto;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DockerServiceImplTest {
    private static final Logger LOG = LoggerFactory.getLogger(DockerServiceImplTest.class);

    DockerServiceImpl dockerService = new DockerServiceImpl();

    @Test
    void dockerInstalled() {
        assertTrue(dockerService.dockerInstalled());
    }

    @Test
    void dockerComposeInstalled() {
        assertTrue(dockerService.dockerComposeInstalled());
    }

    @Test
    void viewBackendDockerfile() {
        // given
        BackendDockerfileDto dto = new BackendDockerfileDto(
                "maven",
                "3.8.5",
                "openjdk",
                "openjdk",
                "17",
                "alpine",
                "workdir",
                "8080",
                "output",
                false
        );
        // when

        try {
            byte[] content = dockerService.viewBackendDockerfile(dto);
            System.out.println(new String(content));
            // then
            assertTrue(content.length > 0);
            assertEquals("""
                    FROM maven:3.8.5-openjdk-17 AS build
                    WORKDIR /workdir
                    COPY . .
                    WORKDIR /workdir/example
                    RUN mvn clean package
                    ##
                    FROM openjdk:17-alpine
                    WORKDIR /workdir
                    COPY --from=build /workdir/example/target .
                    EXPOSE 8080
                    # this should be dynamic as the account sharing app will not be named this all the time
                    # ENTRYPOINT [ "java","-jar","account-sharing-app-0.0.1-SNAPSHOT.jar" ]
                    # an approach m thinking abt is to rename the file
                    RUN mv `ls *.jar | head -1` output.jar
                    ENTRYPOINT [ "java","-jar","output.jar" ]""", new String(content));
        } catch (IOException e) {
            fail();
        }

    }

    @Test
    void viewBackendDockerfileWithoutBuild() {
        // given
        BackendDockerfileDto dto = new BackendDockerfileDto(
                "maven",
                "3.8.5",
                "openjdk",
                "openjdk",
                "17",
                "alpine",
                "example",
                "8080",
                "output",
                true
        );
        // when

        try {
            byte[] content = dockerService.viewBackendDockerfile(dto);
            System.out.println(new String(content));
            // then
            assertTrue(content.length > 0);
            assertEquals("""
                    FROM maven:3.8.5-openjdk-17 AS build
                    WORKDIR /example
                    COPY . .
                    WORKDIR /example/example
                    RUN mvn clean package
                    ##
                    #FROM openjdk:17-alpine
                    #WORKDIR /example
                    #COPY --from=build /example/example/target .
                    #EXPOSE 8080
                    ## this should be dynamic as the account sharing app will not be named this all the time
                    ## ENTRYPOINT [ "java","-jar","account-sharing-app-0.0.1-SNAPSHOT.jar" ]
                    ## an approach m thinking abt is to rename the file
                    #RUN mv `ls *.jar | head -1` output.jar
                    #ENTRYPOINT [ "java","-jar","output.jar" ]""", new String(content));
        } catch (IOException e) {
            fail();
        }

    }

    @Test
    void viewDataBaseDockerfile() {
        // given
        DataBaseDockerfileDto dto = new DataBaseDockerfileDto(
                "postgres",
                "latest",
                "alpine",
                "dbQueries.sql"
        );
        // when

        try {
            byte[] content = dockerService.viewDataBaseDockerfile(dto);
            System.out.println(new String(content));
            // then
            assertTrue(content.length > 0);
            assertEquals("""
                    # base image
                    FROM postgres:latest-alpine
                    # this line is used to serve initializing script to the database
                    COPY dbQueries.sql /docker-entrypoint-initdb.d/""", new String(content));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void viewDataBaseDockerfileWithoutInitQueries() {
        // given
        DataBaseDockerfileDto dto = new DataBaseDockerfileDto(
                "postgres",
                "latest",
                "alpine",
                null
        );
        // when

        try {
            byte[] content = dockerService.viewDataBaseDockerfile(dto);
            System.out.println(new String(content));
            // then
            assertTrue(content.length > 0);
            assertEquals("""
                    # base image
                    FROM postgres:latest-alpine
                    # this line is used to serve initializing script to the database
                    #COPY db-init-queries-filename /docker-entrypoint-initdb.d/""", new String(content));
        } catch (IOException e) {
            fail();
        }
    }
}