package com.winchesters.devopsify.dto.response;

public record TestResultDto(
        int testsRun,
        int failures,
        int errors,
        int skipped
) {
}
