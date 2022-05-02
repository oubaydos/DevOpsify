package com.winchesters.devopsify.technologies.docker;

import com.winchesters.devopsify.utils.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DockerServiceImpl implements DockerService {

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
}
