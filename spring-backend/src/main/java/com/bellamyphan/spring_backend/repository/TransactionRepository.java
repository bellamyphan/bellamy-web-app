package com.bellamyphan.spring_backend.repository;

import com.bellamyphan.spring_backend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Spring Jpa will create basic CRUD methods

    @Query("SELECT t FROM Transaction t " +
            "LEFT JOIN FETCH t.type " +
            "LEFT JOIN FETCH t.bank b " +
            "LEFT JOIN FETCH b.type " +
            "JOIN FETCH t.user " +
            "WHERE t.user.id = :userId")
    List<Transaction> findAllByUserIdWithDetails(@Param("userId") Long userId);

}
