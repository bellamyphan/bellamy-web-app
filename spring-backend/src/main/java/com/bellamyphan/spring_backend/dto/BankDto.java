package com.bellamyphan.spring_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDto {
    private Long id;
    private String name;
    private LocalDate openingDate;
    private LocalDate closingDate;
    private String type;
}
