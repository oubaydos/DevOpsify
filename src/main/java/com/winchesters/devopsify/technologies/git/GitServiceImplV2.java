package com.winchesters.devopsify.technologies.git;

import com.winchesters.devopsify.technologies.Version;

public class GitServiceImplV2 implements GitService{
    public static void main(String[] args){
        GitService gitService = new GitServiceImplV2();
        System.out.println(gitService.getVersion());
    }
    @Override
    public int initializeRepository() {
        return 0;
    }
}
