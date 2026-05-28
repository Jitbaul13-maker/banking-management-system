package com.baul.banking_backend.controllers;

import com.baul.banking_backend.models.DepositDetails;
import com.baul.banking_backend.services.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/{custId}")
public class DepositController {

    private DepositService service;

    public DepositController(DepositService service) {
        this.service = service;
    }

    @PostMapping("/deposits")
    public ResponseEntity<DepositDetails> createDeposit(@PathVariable int custId,
                                           @RequestBody DepositDetails details){
        DepositDetails deposit = service.createDeposit(custId, details);
        return ResponseEntity.ok(deposit);
    }

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable("custId") int custId,
                                           @PathVariable("depositId") int depositId){
        service.deleteDeposit(custId, depositId);
        return ResponseEntity.ok("Deposit deleted successfully");
    }

    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<DepositDetails> getDepositById(@PathVariable("custId") int custId,
                                           @PathVariable("depositId") int depositId){
        DepositDetails deposit = service.getDepositById(custId, depositId);
        return ResponseEntity.ok(deposit);
    }

    @GetMapping("/deposits")
    public ResponseEntity<List<DepositDetails>> getAllDeposit(@PathVariable("custId") int custId){
        List<DepositDetails> deposit = service.getAllDeposit(custId);
        return ResponseEntity.ok(deposit);
    }


}
