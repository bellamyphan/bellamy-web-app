package com.bellamyphan.spring_backend.repository;

import com.bellamyphan.spring_backend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Spring Jpa will create basic CRUD methods
}
