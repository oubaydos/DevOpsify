package com.winchesters.devopsify.technologies.jenkins;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.winchesters.devopsify.model.entity.Server;
import org.springframework.stereotype.Component;

@Component
public class JenkinsClientFactory {

    public JenkinsClient getClient(Server server){
            return JenkinsClient.builder()
                    .endPoint(server.url()) // Optional. Defaults to http://127.0.0.1:8080
                    .credentials(String.format("%s:%s",server.username(),server.password())) // Optional.
                    .build();
    }
}
