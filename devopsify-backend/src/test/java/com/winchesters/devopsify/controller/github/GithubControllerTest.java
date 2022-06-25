//package com.winchesters.devopsify.controller.github;
//
//import com.winchesters.devopsify.controller.GeneralControllerAdviceImpl;
//import com.winchesters.devopsify.service.technologies.github.GithubServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//@ExtendWith(MockitoExtension.class)
//class GithubControllerTest {
//
//    private MockMvc mvc;
//
//    @Mock
//    private GithubServiceImpl githubService;
//    @InjectMocks
//    private GithubController githubController;
//
//    @BeforeEach
//    void setUp() {
//        mvc = MockMvcBuilders.standaloneSetup(githubController)
//                .setControllerAdvice(new GithubControllerAdviceImpl(),new GeneralControllerAdviceImpl())
//                .build();
//    }
//    @Test
//    public void connect() throws Exception {
//        // given
//        // when
////        MockHttpServletResponse successResponse = mvc.perform(
////                        post("/api/v1/github/connect")
////                                .header("personalAccessToken","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"))
////                .andReturn().getResponse();
//        MockHttpServletResponse failureResponse = mvc.perform(
//                        post("/api/v1/github/connect")
//                                .header("personalAccessToken",""))
//                .andReturn().getResponse();
//        MockHttpServletResponse failureResponseWithNull = mvc.perform(
//                        post("/api/v1/github/connect")
//                                .header("personalAccessToken",""))
//                .andReturn().getResponse();
//
//        // then
////        assertThat(successResponse.getStatus()).isEqualTo(HttpStatus.CREATED.value());
//        assertThat(failureResponse.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
////        assertThat(failureResponseWithNull.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
//
//    }
//    @AfterEach
//    void tearDown() {
//    }
//}