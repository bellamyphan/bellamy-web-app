package com.bellamyphan.spring_backend.config;

import com.bellamyphan.spring_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@RequiredArgsConstructor
public class FirstUserConfig {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(FirstUserConfig.class);

    @Bean
    @DependsOn("createFirstRole")
    public CommandLineRunner createFirstUser() {
        logger.info("Creating first user");
        return (args) -> userService.createFirstUser();
    }
}
