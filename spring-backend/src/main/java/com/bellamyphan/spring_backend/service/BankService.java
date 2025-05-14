package com.bellamyphan.spring_backend.service;

import com.bellamyphan.spring_backend.model.Bank;
import com.bellamyphan.spring_backend.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    // Save a bank
    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    // Find by userId
    public List<Bank> getBanksByUserId(Long userId) {
        return bankRepository.findByUserId(userId).orElse(new LinkedList<>());
    }
}
