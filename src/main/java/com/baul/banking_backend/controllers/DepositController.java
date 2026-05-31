package com.baul.banking_backend.controllers;

import com.baul.banking_backend.DTOs.CreateDepositDTO;
import com.baul.banking_backend.models.DepositDetails;
import com.baul.banking_backend.services.DepositService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepositController {

    private final DepositService service;

    public DepositController(DepositService service) {
        this.service = service;
    }

    @PostMapping("/customers/me/deposits")
    public ResponseEntity<DepositDetails> createDeposit(@PathVariable int custId,
                                                        @Valid  @RequestBody CreateDepositDTO deposit){
        DepositDetails depositDetails = service.createDeposit(deposit);
        return ResponseEntity.ok(depositDetails);
    }

    @PatchMapping("/admin/customers/{custId}/deposits/{depositId}/activate")
    public ResponseEntity<?> activateDeposit(@PathVariable("custId") int custId,
                                           @PathVariable("depositId") int depositId){
        service.activateDeposit(custId, depositId);
        return ResponseEntity.ok("Deposit deleted successfully");
    }

    @PatchMapping("/admin/customers/{custId}/deposits/{depositId}/activate")
    public ResponseEntity<?> deactivateDeposit(@PathVariable("custId") int custId,
                                             @PathVariable("depositId") int depositId){
        service.deactivateDeposit(custId, depositId);
        return ResponseEntity.ok("Deposit deleted successfully");
    }

    @GetMapping("/customers/me/deposits/{depositId}")
    public ResponseEntity<DepositDetails> getDepositById(@PathVariable("depositId") int depositId){
        DepositDetails deposit = service.getDepositById(depositId);
        return ResponseEntity.ok(deposit);
    }

    @GetMapping("/customers/me/deposits")
    public ResponseEntity<List<DepositDetails>> getAllDeposit(){
        List<DepositDetails> deposit = service.getAllDeposit();
        return ResponseEntity.ok(deposit);
    }

}
