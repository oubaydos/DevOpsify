package com.winchesters.devopsify.controller.github;

import com.winchesters.devopsify.dto.GithubRepositoryDto;
import com.winchesters.devopsify.service.github.GithubRepositoryImpl;
import com.winchesters.devopsify.service.github.GithubServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/github")
@RequiredArgsConstructor
public class GithubController {
    private static final Logger LOG = LoggerFactory.getLogger(GithubController.class);
    private final GithubServiceImpl githubService;
    private final GithubRepositoryImpl githubRepository;
    @PostMapping("/connect")
    @ResponseStatus( HttpStatus.CREATED )
    private void connect(@RequestHeader(name="Personal-Access-Token") String personalAccessToken) throws IOException {
        githubService.connectToGithub(personalAccessToken);
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
