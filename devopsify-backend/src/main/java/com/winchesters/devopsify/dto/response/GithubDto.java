package com.winchesters.devopsify.dto.response;

import com.winchesters.devopsify.enums.repositoryStatus;
import com.winchesters.devopsify.enums.ReadMeStatus;
import java.util.Date;

public record GithubDto(
        String title,
        ReadMeStatus readMeStatus,
        repositoryStatus repositoryStatus,
        int numberOfCommits,
        Date lastCommitDate
) {
}
