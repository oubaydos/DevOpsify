//package com.winchesters.devopsify.service.technologies.github;
//
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.platform.runner.JUnitPlatform;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@WebAppConfiguration
//@RunWith(JUnitPlatform.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class GithubServiceImplTest {
//    @Autowired
//    WebApplicationContext webApplicationContext;
//    private MockMvc mockMvc;
//
//    @BeforeAll
//    public void setup() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(webApplicationContext)
//                .apply(springSecurity())
//                .build();
//    }
//
//
//    @Test
//    @WithMockUser(username = "oubaydos", password = "12345678", roles = "ADMIN")
//    void getGithub() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/api/v1/github/username")
//                        .accept(MediaType.ALL)
//        )
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("oubaydos")));
//
//    }
//
//    @Test
//    void connectToGithub() {
//    }
//}