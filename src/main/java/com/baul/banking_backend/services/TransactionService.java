package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.TransactionDTO;
import com.baul.banking_backend.exception.InsufficientBalanceException;
import com.baul.banking_backend.exception.ResourceNotfoundException;
import com.baul.banking_backend.models.AccountDetails;
import com.baul.banking_backend.repos.AccountDetailsRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class TransactionService {

    private final AccountDetailsRepo accountDetailsRepo;

    public TransactionService(AccountDetailsRepo accountDetailsRepo) {
        this.accountDetailsRepo = accountDetailsRepo;
    }

    public BigDecimal deposit(int custId, int accountId, TransactionDTO dto) {
        AccountDetails account = accountDetailsRepo.findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("Resource not found"));

        account.setAccountBalance(
                account.getAccountBalance().add(dto.getAmount())
        );

        return account.getAccountBalance();
    }

    public BigDecimal withdraw(int custId, int accountId, TransactionDTO dto) {
        AccountDetails account = accountDetailsRepo.findByAccountIdAndCustomerCustId(accountId, custId)
                .orElseThrow(() -> new ResourceNotfoundException("Resource not found"));

        if (account.getAccountBalance().compareTo(dto.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        account.setAccountBalance(
                account.getAccountBalance().subtract(dto.getAmount())
        );

        return account.getAccountBalance();
    }
}
