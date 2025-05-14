package com.bellamyphan.spring_backend.controller;

import com.bellamyphan.spring_backend.dto.TransactionDto;
import com.bellamyphan.spring_backend.exception.ResourceNotFoundException;
import com.bellamyphan.spring_backend.model.Transaction;
import com.bellamyphan.spring_backend.repository.TransactionRepository;
import com.bellamyphan.spring_backend.service.DtoMapperService;
import com.bellamyphan.spring_backend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final DtoMapperService dtoMapperService;

    // Get all transactions
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Get a transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));
        return ResponseEntity.ok(transaction);
    }

    // Create a new transaction
    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        TransactionDto dto = dtoMapperService.transactionMappingToDto(createdTransaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    // Create some sample transactions per request of user
    @PostMapping("/sample")
    public ResponseEntity<List<TransactionDto>> createSampleTransaction() {
        List<Transaction> transactions = transactionService.createSampleTransactions();
        List<TransactionDto> transactionDtos = new LinkedList<>();
        for (Transaction transaction : transactions) {
            TransactionDto dto = dtoMapperService.transactionMappingToDto(transaction);
            transactionDtos.add(dto);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDtos);
    }

    // Update a transaction by ID
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id,
                                                         @RequestBody Transaction transactionDetails) {
        // Find the existing transaction
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));
        // Update the transaction fields
        transaction.setDate(transactionDetails.getDate());
        transaction.setAmount(transactionDetails.getAmount());
        transaction.setType(transactionDetails.getType());
        transaction.setNotes(transactionDetails.getNotes());
        transaction.setBank(transactionDetails.getBank());
        // Save the updated transaction
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(updatedTransaction);
    }

    // Delete a transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTransaction(@PathVariable Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id " + id));
        transactionRepository.delete(transaction);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
