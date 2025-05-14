package com.bellamyphan.spring_backend.service;

import com.bellamyphan.spring_backend.exception.TransactionTypeCreationException;
import com.bellamyphan.spring_backend.model.TransactionType;
import com.bellamyphan.spring_backend.repository.TransactionTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionTypeService {

    private final TransactionTypeRepository transactionTypeRepository;
    private static final Logger logger = LoggerFactory.getLogger(TransactionTypeService.class);

    public Optional<TransactionType> findById(Long id) {
        return transactionTypeRepository.findById(id);
    }

    @Transactional
    public void createFirstTransactionType() {
        // Create a list of default transaction types.
        List<String> types = List.of(
                "Income",
                "Income Tax",
                "Invest",
                "Savings",
                "Health",
                "College/Work",
                "Car",
                "Gas",
                "Grocery",
                "Government",
                "Housing",
                "Utility",
                "Phone",
                "Pet",
                "Entertainment",
                "Meal",
                "Shop",
                "Clothes",
                "Charity"
        );

        try {
            // Create the default transaction types if they don't already exist
            types.forEach(this::createTransactionTypeIfNotExists);
            logger.info("Default transaction types creation completed");
        } catch (DataAccessException dae) {
            logger.error("Database error while creating transaction types", dae);
            throw new TransactionTypeCreationException("Database error while creating transaction types", dae);
        } catch (Exception ex) {
            logger.error("Unexpected error while creating transaction types", ex);
            throw new TransactionTypeCreationException("Unexpected error while creating transaction types", ex);
        }
    }

    private TransactionType createTransactionTypeIfNotExists(String type) {
        return transactionTypeRepository.findByType(type)
                .map(existingType -> {
                    logger.info("Transaction type '{}' already exists", type);
                    return existingType;
                })
                .orElseGet(() -> {
                    TransactionType transactionType = new TransactionType(type);
                    transactionTypeRepository.save(transactionType);
                    logger.info("Created transaction type: {}", type);
                    return transactionType;
                });
    }
}
