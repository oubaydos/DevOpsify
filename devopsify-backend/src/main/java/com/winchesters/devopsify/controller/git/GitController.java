package com.winchesters.devopsify.controller.git;

import com.winchesters.devopsify.dto.GithubRepositoryDto;
import com.winchesters.devopsify.model.GithubCredentials;
import com.winchesters.devopsify.service.technologies.git.GitServiceImpl;
import com.winchesters.devopsify.service.technologies.github.GithubRepositoryImpl;
import com.winchesters.devopsify.service.technologies.github.GithubServiceImpl;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/git")
@RequiredArgsConstructor
public class GitController {
    private static final Logger LOG = LoggerFactory.getLogger(GitController.class);
    private final GitServiceImpl gitService;

    @PostMapping("/clone")
    @ResponseStatus( HttpStatus.CREATED )
    private void cloneProject() {
        //TODO @hamza
    }
}
