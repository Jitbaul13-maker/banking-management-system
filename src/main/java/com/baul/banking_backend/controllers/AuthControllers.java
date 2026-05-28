package com.baul.banking_backend.controllers;

import com.baul.banking_backend.DTOs.LoginReqDTO;
import com.baul.banking_backend.models.Customer;
import com.baul.banking_backend.services.LogInService;
import com.baul.banking_backend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControllers {
    @Autowired
    private CustomerService userservice;

    @Autowired
    private LogInService logInService;

    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        userservice.registerCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody LoginReqDTO loginReq){
        logInService.verify(loginReq);
        return ResponseEntity.ok().build();
    }
}
