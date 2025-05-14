package com.bellamyphan.spring_backend.service;

import com.bellamyphan.spring_backend.dto.BankDto;
import com.bellamyphan.spring_backend.model.Bank;
import com.bellamyphan.spring_backend.model.User;
import com.bellamyphan.spring_backend.repository.BankRepository;
import com.bellamyphan.spring_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;
    private final UserRepository userRepository;
    private final DtoMapperService dtoMapperService;

    // Save a bank
    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    // Find by userId
    public List<Bank> getBanksByUserId(Long userId) {
        return bankRepository.findByUserId(userId).orElse(new LinkedList<>());
    }

    public List<BankDto> getBanksByAuthenticatedUser() {
        // Get user by the jwt token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find the user with username: " + username));
        // Get banks by user id
        List<Bank> banks = bankRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("No banks found for user id: " + user.getId()));
        // Map to dto
        return banks.stream()
                .map(dtoMapperService::bankMappingToDto)
                .toList();
    }
}
