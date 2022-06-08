package com.winchesters.devopsify.dto.response;

import java.util.Map;

public record DockerfileDefaultValuesDto(
        Map<String,String> backend,
        Map<String,String> db
) {
}
