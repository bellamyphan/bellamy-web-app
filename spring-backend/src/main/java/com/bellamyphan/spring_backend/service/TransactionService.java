package com.bellamyphan.spring_backend.service;

import com.bellamyphan.spring_backend.model.Transaction;
import com.bellamyphan.spring_backend.model.User;
import com.bellamyphan.spring_backend.repository.TransactionRepository;
import com.bellamyphan.spring_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public Transaction createTransaction(Transaction transaction) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find the user with username: " + username));
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }
}
