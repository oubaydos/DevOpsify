package com.winchesters.devopsify.model;

import com.winchesters.devopsify.enums.ReadMeStatus;
import com.winchesters.devopsify.enums.RepositoryStatus;

import java.util.Date;

public record GithubAnalyseResults(
        String title,
        ReadMeStatus readMeStatus,
        RepositoryStatus repositoryStatus,
        int numberOfCommits,
        Date lastCommitDate
){
}
