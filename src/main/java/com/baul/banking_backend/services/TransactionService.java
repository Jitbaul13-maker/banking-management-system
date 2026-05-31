package com.baul.banking_backend.services;

import com.baul.banking_backend.DTOs.TransactionDTO;
import com.baul.banking_backend.exception.InactiveAccountException;
import com.baul.banking_backend.exception.InsufficientBalanceException;
import com.baul.banking_backend.exception.ResourceNotfoundException;
import com.baul.banking_backend.models.AccountDetails;
import com.baul.banking_backend.models.User;
import com.baul.banking_backend.repos.AccountDetailsRepo;
import com.baul.banking_backend.repos.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class TransactionService {

    private final AccountDetailsRepo accountDetailsRepo;
    @Autowired
    private CustomerRepo customerRepo;

    public TransactionService(AccountDetailsRepo accountDetailsRepo) {
        this.accountDetailsRepo = accountDetailsRepo;
    }

    public int getCurrentUser(){
       MyUserPrinciple user = (MyUserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       return user.getUserId();
    }


    public BigDecimal deposit(int accountId, TransactionDTO dto){

        int userId = getCurrentUser();

        User user = customerRepo.findById(userId).orElseThrow(()->new ResourceNotfoundException("Account Not found"));

        AccountDetails account = accountDetailsRepo
                .findByCustomerCustIdAndAccountId(userId, accountId)
                .orElseThrow(() -> new ResourceNotfoundException("No valid account found"));

        if (!account.getActive() || !user.getActive()){
            throw new InactiveAccountException("Operation not allowed on inactive account");
        }

        account.setAccountBalance(account.getAccountBalance().add(dto.getAmount()));

        return account.getAccountBalance();
    }

    public BigDecimal withdraw(int accountId, TransactionDTO dto){

        int userId = getCurrentUser();
        User user = customerRepo.findById(userId).orElseThrow(()->new ResourceNotfoundException("Account Not found"));

        AccountDetails account = accountDetailsRepo
                .findByCustomerCustIdAndAccountId(userId, accountId)
                .orElseThrow(() -> new ResourceNotfoundException("No valid account found"));

        if (!account.getActive() || !user.getActive()){
            throw new InactiveAccountException("Operation not allowed on inactive account");
        }

        if (account.getAccountBalance().compareTo(dto.getAmount()) < 0){
            throw  new InsufficientBalanceException("Insufficient Balance!!!");
        }

        account.setAccountBalance(account.getAccountBalance().subtract(dto.getAmount()));

        return account.getAccountBalance();
    }
}
