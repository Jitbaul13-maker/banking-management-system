package com.baul.banking_backend.controllers;

import com.baul.banking_backend.DTOs.CreateAccountDTO;
import com.baul.banking_backend.models.AccountDetails;
import com.baul.banking_backend.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping("/admin/customers/{custId}/accounts")
    public ResponseEntity<List<AccountDetails>> getAllAccounts(@PathVariable("custId") int custId){
        List<AccountDetails> accountDetails = service.getAllAccounts(custId);
        return ResponseEntity.ok(accountDetails);
    }

    @GetMapping("/customers/{custId}/accounts/{accountId}")
    public ResponseEntity<AccountDetails> getAccountById(@PathVariable("accountId") int accountId,
                                                         @PathVariable("custId") int custId){
        AccountDetails account = service.getAccountById(accountId, custId);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/customers/{custId}/accounts")
    public ResponseEntity<?> createAccount(@PathVariable("custId") int custId,
                                           @Valid @RequestBody CreateAccountDTO account){
        service.createAccount(custId, account);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/admin/customers/{custId}/accounts/{accountId}/activate")
    public ResponseEntity<?> activateAccount(@PathVariable("accountId") int accountId,
                                             @PathVariable("custId") int custId){
        service.activateAccount(accountId, custId);
        return ResponseEntity.ok("Account activated successfully");
    }

    @PatchMapping("/admin/customers/{custId}/accounts/{accountId}/deactivate")
    public ResponseEntity<?> deactivateAccount(@PathVariable("accountId") int accountId,
                                               @PathVariable("custId") int custId){
        service.deactivateAccount(accountId, custId);
        return ResponseEntity.ok("Account deactivated successfully");
    }

}
