package com.winchesters.devopsify.config;

import com.winchesters.devopsify.model.User;
import com.winchesters.devopsify.repository.UserRepository;
import com.winchesters.devopsify.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AppConfig(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            User hamza = new User(
                    1L,
                    "levi",
                    "benyazidhamza969@gmail.com",
                    passwordEncoder.encode("12345678"),
                    ApplicationUserRole.ADMIN
            );
            User oubaydos = new User(
                    2L,
                    "oubaydos",
                    "oubaydos@gmail.com",
                    passwordEncoder.encode("password"),
                    ApplicationUserRole.ADMIN
            );
            hamza=userRepository.save(hamza);
            oubaydos=userRepository.save(oubaydos);

        };
    }
}
