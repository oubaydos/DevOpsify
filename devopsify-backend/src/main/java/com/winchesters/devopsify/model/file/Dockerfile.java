package com.winchesters.devopsify.model.file;

import java.util.List;

public class Dockerfile {
    String image;
    int port;
    List<String> environmentVariables;
    String entrypoint;
}
