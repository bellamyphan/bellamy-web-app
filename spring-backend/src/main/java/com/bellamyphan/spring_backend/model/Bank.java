package com.bellamyphan.spring_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "banks")
@Data // Lombok will generate getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor
public class Bank {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "opening_date", nullable = false)
    private LocalDate openingDate;
    @Column(name = "closing_date")
    private LocalDate closingDate;
    @ManyToOne(optional = false, fetch = FetchType.LAZY) // Foreign key relationship
    @JoinColumn(name = "type_id", referencedColumnName = "id") // Foreign key column in 'transaction_banks' table
    private BankType type;

    // Constructor with parameters excluding id
    public Bank(String name, LocalDate openingDate, LocalDate closingDate, BankType type) {
        this.name = name;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.type = type;
    }
}
