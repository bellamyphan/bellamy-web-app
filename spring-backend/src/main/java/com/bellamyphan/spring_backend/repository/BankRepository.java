package com.bellamyphan.spring_backend.repository;

import com.bellamyphan.spring_backend.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    // Jpa will generate basic CRUD methods

    Optional<List<Bank>> findByUserId(Long userId);
}
