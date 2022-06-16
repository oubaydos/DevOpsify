package com.winchesters.devopsify.service.technologies.jenkins;

import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.winchesters.devopsify.exception.jenkins.JenkinsServerException;
import com.winchesters.devopsify.model.entity.Server;
import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JenkinsServiceImplTest {
    private static final Logger LOG = LoggerFactory.getLogger(JenkinsServiceImplTest.class);
    JenkinsServiceImpl jenkinsService = new JenkinsServiceImpl(new JenkinsClientFactory());

    String createdPipelineName = null;

    @BeforeAll
    void initClient() {
        jenkinsService.setJenkinsClient(
                new Server(
                        "http://188.166.100.241:8080/",
                        "devopsify",
                        "devopsify"
                )
        );
    }

    @AfterEach
    void testCleanUp() {
        if (createdPipelineName != null)
            jenkinsService.deleteJob(createdPipelineName);
        createdPipelineName = null;
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    void pingJenkinsServer() {
        try {
            jenkinsService.pingJenkinsServer();
        } catch (JenkinsServerException e) {
            fail();
        }
    }

//    @Test
//    void createApiToken() {
//        // given
//        // when
//        String token = jenkinsService.createApiToken("temp");
//        // then
//        Assertions.assertNotNull(token);
//        Assertions.assertEquals(34, token.length());
//    }

//    @Test
//    void createPipeline() {
//        // given
//        String pipelineName = "temp-pipeline-for-test";
//        createdPipelineName = pipelineName;
//        // when
//        jenkinsService.createPipeline("https://github.com/temp-devopsify/waaaaaaaa", pipelineName, "token");
//        JobInfo jobInfo = jenkinsService.getJobInfoByName(pipelineName);
//        // then
//        assertNotNull(jobInfo);
//        assertEquals(pipelineName, jobInfo.name());
//    }
    @Test
    void del(){
        jenkinsService.deleteAllJobs();
    }

}