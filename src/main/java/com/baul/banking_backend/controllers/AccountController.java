package com.baul.banking_backend.controllers;

import com.baul.banking_backend.models.AccountDetails;
import com.baul.banking_backend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/{custId}")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDetails>> getAllAccounts(@PathVariable("custId") int custId){
        List<AccountDetails> accountDetails = service.getAllAccounts(custId);
        return ResponseEntity.ok(accountDetails);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountDetails> getAccountById(@PathVariable("accountId") int accountId,
                                                         @PathVariable("custId") int custId){
        AccountDetails account = service.getAccountById(accountId, custId);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@PathVariable("custId") int custId,
                                           @RequestBody AccountDetails details){
        service.createAccount(custId, details);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/accounts/{accountId}/activate")
    public ResponseEntity<?> activateAccount(@PathVariable("accountId") int accountId,
                                             @PathVariable("custId") int custId){
        service.activateAccount(accountId, custId);
        return ResponseEntity.ok("Account activated successfully");
    }

    @PutMapping("/accounts/{accountId}/deactivate")
    public ResponseEntity<?> deactivateAccount(@PathVariable("accountId") int accountId,
                                               @PathVariable("custId") int custId){
        service.deactivateAccount(accountId, custId);
        return ResponseEntity.ok("Account deactivated successfully");
    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("custId") int custId,
                                           @PathVariable("accountId") int accountId){
        service.deleteAccount(custId, accountId);
        return ResponseEntity.ok("Account deleted successfully");
    }

}
