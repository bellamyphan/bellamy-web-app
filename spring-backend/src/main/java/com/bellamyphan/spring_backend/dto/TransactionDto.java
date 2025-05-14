package com.bellamyphan.spring_backend.dto;

import com.bellamyphan.spring_backend.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String notes;
    private String typeName;
    private String bankName;
    private String username;

    public TransactionDto(Transaction t) {
        this.id = t.getId();
        this.date = t.getDate();
        this.amount = t.getAmount();
        this.notes = t.getNotes();
        this.typeName = t.getType() != null ? t.getType().getType() : null;
        this.bankName = t.getBank() != null ? t.getBank().getName() : null;
        this.username = t.getUser().getUsername();
    }
}
