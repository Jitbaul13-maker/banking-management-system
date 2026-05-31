package com.baul.banking_backend.controllers;

import com.baul.banking_backend.DTOs.TransactionDTO;
import com.baul.banking_backend.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/customers/me/accounts/{accountId}")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PatchMapping("/deposit")
    public ResponseEntity<BigDecimal> deposit(@PathVariable("accountId") int accountId,
                                                @Valid @RequestBody TransactionDTO dto){
        BigDecimal updatedAmount = transactionService.deposit(accountId, dto);
        return ResponseEntity.ok(updatedAmount);
    }

    @PatchMapping("/withdraw")
    public ResponseEntity<BigDecimal> withdraw(@PathVariable("accountId") int accountId,
                                                @Valid @RequestBody TransactionDTO dto){
        BigDecimal updatedAmount = transactionService.withdraw(accountId, dto);
        return ResponseEntity.ok(updatedAmount);
    }
}
