package com.winchesters.devopsify.model;

public record GithubCredentials(
        String username,
        String personalAccessToken
) {
}
