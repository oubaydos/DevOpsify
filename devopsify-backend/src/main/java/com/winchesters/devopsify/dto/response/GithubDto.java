package com.winchesters.devopsify.dto.response;

import com.winchesters.devopsify.enums.RepositoryStatus;
import com.winchesters.devopsify.enums.ReadMeStatus;
import java.util.Date;

public record GithubDto(
        String title,
        ReadMeStatus readMeStatus,
        RepositoryStatus repositoryStatus,
        int numberOfCommits,
        Date lastCommitDate
) {
}
