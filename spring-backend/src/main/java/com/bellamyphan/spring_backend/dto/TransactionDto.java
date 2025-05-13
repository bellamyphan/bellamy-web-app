package com.bellamyphan.spring_backend.dto;

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
}
