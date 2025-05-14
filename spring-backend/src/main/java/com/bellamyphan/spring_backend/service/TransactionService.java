package com.bellamyphan.spring_backend.service;

import com.bellamyphan.spring_backend.model.*;
import com.bellamyphan.spring_backend.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BankTypeRepository bankTypeRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final BankRepository bankRepository;
    private final BankService bankService;

    public Transaction createTransaction(Transaction transaction) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find the user with username: " + username));
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public List<Transaction> createSampleTransactions() {
        // Fetch User from the jwt token
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find the user with username: " + username));
        // Load a default bank type
        BankType bankType = bankTypeRepository.findById(1L).orElseThrow(
                () -> new RuntimeException("Default BankType not found"));
        // Load a default transactionType
        TransactionType transactionType = transactionTypeRepository.findById(1L).orElseThrow(
                () -> new RuntimeException("TransactionType not found"));
        // Create a sample bank
        List<Bank> banks = bankService.getBanksByUserId(user.getId());
        if (banks.isEmpty()) {
            Bank bank = new Bank(
                    "Sample Bank",
                    LocalDate.of(2020, 1, 1),
                    null,
                    bankType,
                    user
            );
            bank = bankRepository.save(bank);
            banks.add(bank);
        }
        // Create 5 sample transactions
        List<Transaction> transactions = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            Transaction transaction = new Transaction(
                    LocalDate.now().minusDays(5 - i),
                    100.0 * i,
                    transactionType,
                    "Sample transaction " + i,
                    banks.getFirst(),
                    user
            );
            transactions.add(transactionRepository.save(transaction));
        }
        logger.info("Sample bank and transactions created successfully for username: {}", user.getUsername());
        return transactions;
    }

//    public List<TransactionDto> getTransactionsByUserId() {
//
//    }
}
