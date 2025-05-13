package com.bellamyphan.spring_backend.service;

import com.bellamyphan.spring_backend.exception.BankTypeCreationException;
import com.bellamyphan.spring_backend.model.BankType;
import com.bellamyphan.spring_backend.repository.BankTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankTypeService {

    private final BankTypeRepository bankTypeRepository;
    private static final Logger logger = LoggerFactory.getLogger(BankTypeService.class);

    @Transactional
    public void createFirstBankType() {
        try {
            // Create or get the default checking bank type
            BankType checkingType = createBankTypeIfNotExists("Checking");
            // Create or get the default savings bank type
            BankType savingsType = createBankTypeIfNotExists("Savings");
            // Create or get the default credit bank type
            BankType creditType = createBankTypeIfNotExists("Credit");
            // Create or get the default rewards bank type
            BankType rewardsType = createBankTypeIfNotExists("Rewards");
        } catch (DataAccessException dae) {
            logger.error("Database error while creating bank types", dae);
            throw new BankTypeCreationException("Database error while creating bank types", dae);
        } catch (Exception ex) {
            logger.error("Unexpected error while creating bank types", ex);
            throw new BankTypeCreationException("Unexpected error while creating bank types", ex);
        }
    }

    private BankType createBankTypeIfNotExists(String bankType) {
        return bankTypeRepository.findByType(bankType)
                .map(existingType -> {
                    logger.info("BankType '{}' already exists", bankType);
                    return existingType;
                })
                .orElseGet(() -> {
                    BankType type = new BankType(bankType);
                    bankTypeRepository.save(type);
                    logger.info("Created bank type: {}", bankType);
                    return type;
                });
    }
}
