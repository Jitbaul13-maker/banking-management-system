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

    @PostMapping("/customers/{custId}/deposits")
    public ResponseEntity<DepositDetails> createDeposit(@PathVariable int custId,
                                                        @Valid  @RequestBody CreateDepositDTO deposit){
        DepositDetails depositDetails = service.createDeposit(custId, deposit);
        return ResponseEntity.ok(depositDetails);
    }

    @DeleteMapping("/admin/customers/{custId}/deposits/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable("custId") int custId,
                                           @PathVariable("depositId") int depositId){
        service.deleteDeposit(custId, depositId);
        return ResponseEntity.ok("Deposit deleted successfully");
    }

    @GetMapping("/customers/{custId}/deposits/{depositId}")
    public ResponseEntity<DepositDetails> getDepositById(@PathVariable("custId") int custId,
                                           @PathVariable("depositId") int depositId){
        DepositDetails deposit = service.getDepositById(custId, depositId);
        return ResponseEntity.ok(deposit);
    }

    @GetMapping("/customers/{custId}/deposits")
    public ResponseEntity<List<DepositDetails>> getAllDeposit(@PathVariable("custId") int custId){
        List<DepositDetails> deposit = service.getAllDeposit(custId);
        return ResponseEntity.ok(deposit);
    }

}
