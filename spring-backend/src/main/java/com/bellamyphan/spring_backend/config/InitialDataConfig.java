package com.bellamyphan.spring_backend.config;

import com.bellamyphan.spring_backend.service.BankTypeService;
import com.bellamyphan.spring_backend.service.RoleService;
import com.bellamyphan.spring_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitialDataConfig {

    private final RoleService roleService;
    private final UserService userService;
    private final BankTypeService bankTypeService;
    private static final Logger logger = LoggerFactory.getLogger(InitialDataConfig.class);

    @Bean
    public CommandLineRunner initData() {
        return (args) -> {
            logger.info("Creating roles...");
            roleService.createFirstRole();

            logger.info("Creating first user...");
            userService.createFirstUser();

            logger.info("Creating bank types...");
            bankTypeService.createFirstBankType();
        };
    }
}
