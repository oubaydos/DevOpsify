package com.winchesters.devopsify.dto;

public record TestResultDto(
        int testsRun,
        int failures,
        int errors,
        int skipped
) {
}
