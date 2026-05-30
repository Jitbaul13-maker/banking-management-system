package com.baul.banking_backend.controllers;

import com.baul.banking_backend.DTOs.UpdateUserDTO;
import com.baul.banking_backend.models.Customer;
import com.baul.banking_backend.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService userservice;

    public CustomerController(CustomerService userservice) {
        this.userservice = userservice;
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return ResponseEntity.ok(userservice.getAllCustomer());
    }

    @GetMapping("/{custId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("custId") int custId){
        Customer customer = userservice.getCustomerById(custId);
        if (customer != null){
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{custId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("custId") int custId,
                                                   @Valid @RequestBody UpdateUserDTO user){
        Customer customer1 = userservice.updateCustomer(custId, user);
        return ResponseEntity.ok(customer1);
    }
    @PatchMapping("/{custId}/activate")
    public ResponseEntity<Customer> activateCustomer(@PathVariable("custId") int custId){
        Customer customer = userservice.activateCustomer(custId);
        return ResponseEntity.ok(customer);
    }

    @PatchMapping("/{custId}/deactivate")
    public ResponseEntity<Customer> deactivateCustomer(@PathVariable("custId") int custId){
        Customer customer= userservice.deactivateCustomer(custId);
        return ResponseEntity.ok(customer);
    }
}
