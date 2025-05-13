package com.bellamyphan.spring_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
@Data // Lombok will generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "amount", nullable = false)
    @NotNull
    private Double amount;

    // Some transactions do not have a type, only track the bank for bank balances
    @ManyToOne(fetch = FetchType.LAZY) // Foreign key relationship
    @JoinColumn(name = "type_id", referencedColumnName = "id") // Foreign key column in 'transactions' table
    private TransactionType type;

    @Column(name = "notes")
    private String notes;

    // Some transactions do not need a bank, simply to track transaction_type
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", referencedColumnName = "id") // Foreign key column in 'transactions' table
    private Bank bank;

    // Each transaction must be associated to a user
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    // Constructor with parameters excluding id
    public Transaction(LocalDate date, Double amount, TransactionType type, String notes, Bank bank, User user) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.notes = notes;
        this.bank = bank;
        this.user = user;
    }
}
