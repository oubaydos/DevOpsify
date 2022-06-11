package com.winchesters.devopsify.model;

public record Credentials (
        String credentialsId,
        String username,
        String secret
){
}
