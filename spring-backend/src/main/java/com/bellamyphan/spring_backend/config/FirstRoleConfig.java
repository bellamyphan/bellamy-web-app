package com.bellamyphan.spring_backend.config;

import com.bellamyphan.spring_backend.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FirstRoleConfig {

    private final RoleService roleService;
    private static final Logger logger = LoggerFactory.getLogger(FirstRoleConfig.class);

    @Bean
    public CommandLineRunner createFirstRole() {
        logger.info("Creating first roles");
        return (args) -> roleService.createFirstRole();
    }
}
