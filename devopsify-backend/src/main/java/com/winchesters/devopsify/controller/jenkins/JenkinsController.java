package com.winchesters.devopsify.controller.jenkins;


import com.winchesters.devopsify.model.entity.Server;
import com.winchesters.devopsify.service.technologies.jenkins.JenkinsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/jenkins")
@RequiredArgsConstructor
public class JenkinsController {

    private final JenkinsService jenkinsService;
    @PostMapping
    public void testConnection(@RequestBody Server server){
        jenkinsService.pingJenkinsServer(server);
    }
}
