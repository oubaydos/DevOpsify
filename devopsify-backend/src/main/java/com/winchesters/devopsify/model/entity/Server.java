package com.winchesters.devopsify.model.entity;

public record Server(
        String url,
        String username,
        String password
) {
}
