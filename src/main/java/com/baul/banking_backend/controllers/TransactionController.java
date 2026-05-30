package com.baul.banking_backend.controllers;

import com.baul.banking_backend.DTOs.TransactionDTO;
import com.baul.banking_backend.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/customers/{custId}/accounts/{accountId}")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PatchMapping("/deposit")
    public ResponseEntity<BigDecimal> deposit(@PathVariable("custId") int custId,
                                     @PathVariable("accountId") int accountId,
                                     @Valid @RequestBody TransactionDTO dto){
        BigDecimal updatedAmount = transactionService.deposit(custId, accountId, dto);
        return ResponseEntity.ok(updatedAmount);
    }

    @PatchMapping("/withdraw")
    public ResponseEntity<BigDecimal> withdraw(@PathVariable("custId") int custId,
                                              @PathVariable("accountId") int accountId,
                                              @Valid @RequestBody TransactionDTO dto){
        BigDecimal updatedAmount = transactionService.withdraw(custId, accountId, dto);
        return ResponseEntity.ok(updatedAmount);
    }
}
