package com.bellamyphan.spring_backend.repository;

import com.bellamyphan.spring_backend.model.BankType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankTypeRepository extends JpaRepository<BankType, Long> {
    // Jpa will generate basic CRUD methods here.

    Optional<BankType> findByType(String type);
}
