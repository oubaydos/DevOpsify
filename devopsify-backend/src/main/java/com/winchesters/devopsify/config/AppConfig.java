package com.winchesters.devopsify.config;

import com.winchesters.devopsify.model.GithubCredentials;
import com.winchesters.devopsify.model.entity.User;
import com.winchesters.devopsify.repository.UserRepository;
import com.winchesters.devopsify.security.ApplicationUserRole;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final PasswordEncoder passwordEncoder;
    private final Logger LOG = LoggerFactory.getLogger(AppConfig.class);
    private final UserRepository userRepository;



    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            String password = "12345678";
            User hamza = new User(
                    1L,
                    "levi",
                    "benyazidhamza969@gmail.com",
                    passwordEncoder.encode(password),
                    ApplicationUserRole.ADMIN,
                    new GithubCredentials(
                            "temp-devopsify",
                            "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"
                    )
            );
            User oubaydos = new User(
                    2L,
                    "oubaydos",
                    "oubaydos@gmail.com",
                    passwordEncoder.encode(password),
                    ApplicationUserRole.CONTRIBUTOR,
                    new GithubCredentials(
                            "temp-devopsify",
                            "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"
                    )
            );
            LOG.info("");
            hamza=userRepository.save(hamza);
            LOG.info(String.format("user %s with password %s is saved to the database.",hamza.getUsername(),password));
            oubaydos=userRepository.save(oubaydos);
            LOG.info(String.format("user %s with password %s is saved to the database.",oubaydos.getUsername(),password));

        };
    }
}
