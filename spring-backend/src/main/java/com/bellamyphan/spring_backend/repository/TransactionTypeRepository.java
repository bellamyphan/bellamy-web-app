package com.bellamyphan.spring_backend.repository;

import com.bellamyphan.spring_backend.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
    // Jpa will generate all basic CRUD methods.

    Optional<TransactionType> findByType(String type);
}
