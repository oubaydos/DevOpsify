package com.winchesters.devopsify.service.github;

public interface GithubRepository {
    /**
     * create a new repository in github
     * @param name of the repository
     */
    void createRepository(String name);
}
