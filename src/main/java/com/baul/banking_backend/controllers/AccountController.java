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

    @GetMapping("/admin/customers/me/accounts")
    public ResponseEntity<List<AccountDetails>> getAllAccounts(){
        List<AccountDetails> accountDetails = service.getAllAccounts();
        return ResponseEntity.ok(accountDetails);
    }

    @GetMapping("/customers/me/accounts/{accountId}")
    public ResponseEntity<AccountDetails> getAccountById(@PathVariable("accountId") int accountId){
        AccountDetails account = service.getAccountById(accountId);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/customers/me/accounts")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountDTO account){
        service.createAccount(account);
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
