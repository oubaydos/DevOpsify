package com.winchesters.devopsify.controller.jenkins;


import com.winchesters.devopsify.exception.jenkins.JenkinsException;
import com.winchesters.devopsify.model.entity.Server;
import com.winchesters.devopsify.service.technologies.jenkins.JenkinsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/jenkins")
@RequiredArgsConstructor
public class JenkinsController {

    private final JenkinsService jenkinsService;

    /**
     * test connection
     * @param server Server object containing jenkins server url and credentials to access that server
     * @throws JenkinsException if the connection failed
     */
    @PostMapping
    public void testConnection(@RequestBody Server server) throws JenkinsException {
        jenkinsService.pingJenkinsServer(server);
    }
}
