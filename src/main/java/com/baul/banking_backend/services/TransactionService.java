package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.TransactionDTO;
import com.baul.banking_backend.models.AccountDetails;
import com.baul.banking_backend.repos.AccountDetailsRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class TransactionService {

    private AccountDetailsRepo accountDetailsRepo;

    public TransactionService(AccountDetailsRepo accountDetailsRepo) {
        this.accountDetailsRepo = accountDetailsRepo;
    }

    public BigDecimal deposit(int custId, int accountId, TransactionDTO dto) {
        AccountDetails account = accountDetailsRepo.findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(() -> new RuntimeException("invalid account details"));

        if (dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        account.setAccountBalance(
                account.getAccountBalance().add(dto.getAmount())
        );

        return account.getAccountBalance();
    }

    public BigDecimal withdraw(int custId, int accountId, TransactionDTO dto) {
        AccountDetails account = accountDetailsRepo.findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(() -> new RuntimeException("invalid account details"));

        if (account.getAccountBalance().compareTo(dto.getAmount()) < 0) {
            throw new RuntimeException("Insufficient Balance");
        }

        if (dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        account.setAccountBalance(
                account.getAccountBalance().subtract(dto.getAmount())
        );

        return account.getAccountBalance();
    }
}
