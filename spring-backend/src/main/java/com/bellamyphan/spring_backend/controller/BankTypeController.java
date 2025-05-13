package com.bellamyphan.spring_backend.controller;

import com.bellamyphan.spring_backend.model.BankType;
import com.bellamyphan.spring_backend.repository.BankTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banks/types")
@RequiredArgsConstructor
public class BankTypeController {

    private final BankTypeRepository bankTypeRepository;

    // Get all bank types
    @GetMapping
    public List<BankType> getAllBankTypes() {
        return bankTypeRepository.findAll();
    }
}
