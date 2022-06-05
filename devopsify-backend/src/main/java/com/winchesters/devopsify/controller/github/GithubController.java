package com.winchesters.devopsify.controller.github;

import com.winchesters.devopsify.dto.GithubRepositoryDto;
import com.winchesters.devopsify.model.GithubCredentials;
import com.winchesters.devopsify.service.technologies.github.GithubRepositoryServiceImpl;
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
@RequestMapping(path = "api/v1/github")
@RequiredArgsConstructor
public class GithubController {
    private static final Logger LOG = LoggerFactory.getLogger(GithubController.class);
    private final GithubServiceImpl githubService;
    private final GithubRepositoryServiceImpl githubRepository;
    @PostMapping("/connect")
    @ResponseStatus( HttpStatus.CREATED )
    private void connect(@RequestBody GithubCredentials githubCredentials) throws IOException {
        githubService.connectToGithub(githubCredentials);
    }
    @PostMapping(path = "/create/repository",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus( HttpStatus.CREATED )
    private void createRepository(@Valid @RequestBody GithubRepositoryDto githubRepositoryDto) throws IOException {
        GHRepository repository = githubRepository.createRepository(githubRepositoryDto);
    }
    @GetMapping("/username")
    private String getUsername() throws IOException {
        return githubService.getGithub().getMyself().getLogin();
    }
}
