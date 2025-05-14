package com.bellamyphan.spring_backend.service;

import com.bellamyphan.spring_backend.dto.BankDto;
import com.bellamyphan.spring_backend.dto.TransactionDto;
import com.bellamyphan.spring_backend.model.Bank;
import com.bellamyphan.spring_backend.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class DtoMapperService {

    public TransactionDto transactionMappingToDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setId(transaction.getId());
        dto.setDate(transaction.getDate());
        dto.setAmount(transaction.getAmount());
        dto.setNotes(transaction.getNotes());
        if (transaction.getType() != null) {
            dto.setTypeName(transaction.getType().getType());
        }
        if (transaction.getBank() != null) {
            dto.setBankName(transaction.getBank().getName());
        }
        if (transaction.getUser() != null) {
            dto.setUsername(transaction.getUser().getUsername());
        }
        return dto;
    }

    public BankDto bankMappingToDto(Bank bank) {
        BankDto dto = new BankDto();
        dto.setId(bank.getId());
        dto.setName(bank.getName());
        if (bank.getType() != null) {
            dto.setType(bank.getType().getType());
        }
        dto.setOpeningDate(bank.getOpeningDate());
        dto.setClosingDate(bank.getClosingDate());
        return dto;
    }
}
