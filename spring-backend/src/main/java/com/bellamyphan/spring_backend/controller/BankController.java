package com.bellamyphan.spring_backend.controller;

import com.bellamyphan.spring_backend.model.Bank;
import com.bellamyphan.spring_backend.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankRepository bankRepository;

    @PostMapping
    ResponseEntity<Bank> createNewBank(@RequestBody Bank bank) {
        Bank newBank = bankRepository.save(bank);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBank);
    }
}
