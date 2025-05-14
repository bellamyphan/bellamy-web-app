package com.bellamyphan.spring_backend.controller;

import com.bellamyphan.spring_backend.dto.BankDto;
import com.bellamyphan.spring_backend.model.Bank;
import com.bellamyphan.spring_backend.model.User;
import com.bellamyphan.spring_backend.service.BankService;
import com.bellamyphan.spring_backend.service.DtoMapperService;
import com.bellamyphan.spring_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController {

    private static final Logger logger = LoggerFactory.getLogger(BankController.class);

    private final BankService bankService;
    private final UserService userService;
    private final DtoMapperService dtoMapperService;

    @PostMapping
    ResponseEntity<Bank> createNewBank(@RequestBody Bank bank) {
        // Get user by the jwt token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        // Set up input bank
        bank.setUser(user);
        bank.setId(null);
        Bank newBank = bankService.saveBank(bank);
        logger.info("Username {} created a new bank: {}", username, newBank);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBank);
    }

    // Get all banks by authenticated user
    @GetMapping
    public ResponseEntity<List<BankDto>> getAllBanksByUserId() {
        List<BankDto> bankDtos = bankService.getBanksByAuthenticatedUser();
        return ResponseEntity.ok(bankDtos);
    }
}
