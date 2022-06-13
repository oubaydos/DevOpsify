package com.winchesters.devopsify.model;

import com.winchesters.devopsify.model.entity.Server;

public record Credentials(
        String credentialsId,
        String username,
        String secret
) {

    public Credentials(String credentialsId, Server server) {
        this(credentialsId, server.username(), server.password());
    }
}
