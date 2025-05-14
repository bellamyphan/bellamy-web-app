package com.bellamyphan.spring_backend.controller;

import com.bellamyphan.spring_backend.model.Bank;
import com.bellamyphan.spring_backend.model.User;
import com.bellamyphan.spring_backend.repository.BankRepository;
import com.bellamyphan.spring_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);

    private final BankRepository bankRepository;
    private final UserRepository userRepository;

    @PostMapping
    ResponseEntity<Bank> createNewBank(@RequestBody Bank bank) {
        // Get user by the jwt token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find the user with username: " + username));
        bank.setUser(user);
        bank.setId(null);
        Bank newBank = bankRepository.save(bank);
        logger.info("Username {} created a new bank: {}", username, newBank);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBank);
    }
}
