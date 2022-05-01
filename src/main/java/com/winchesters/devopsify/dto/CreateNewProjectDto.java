package com.winchesters.devopsify.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CreateNewProjectDto {
    private String name;
    private String location;
    private boolean initGitRepository;
}
