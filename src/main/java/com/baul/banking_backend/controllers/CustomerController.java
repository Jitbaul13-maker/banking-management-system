package com.baul.banking_backend.controllers;

import com.baul.banking_backend.DTOs.UpdateUserDTO;
import com.baul.banking_backend.models.User;
import com.baul.banking_backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final UserService userservice;

    public CustomerController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping("/admin/customers")
    public ResponseEntity<List<User>> getAllCustomer(){
        return ResponseEntity.ok(userservice.getAllCustomer());
    }

    @GetMapping("/customers/me")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(userservice.getCurrentUser());
    }

    @PatchMapping("/customers/me")
    public ResponseEntity<User> updateCurrentUser(
            @Valid @RequestBody UpdateUserDTO dto) {

        return ResponseEntity.ok(
                userservice.updateCurrentUser(dto)
        );
    }

    @PatchMapping("/admin/customers/{custId}/activate")
    public ResponseEntity<User> activateCustomer(@PathVariable("custId") int custId){
        User customer = userservice.activateCustomer(custId);
        return ResponseEntity.ok(customer);
    }

    @PatchMapping("/admin/customers/{custId}/deactivate")
    public ResponseEntity<User> deactivateCustomer(@PathVariable("custId") int custId){
        User customer= userservice.deactivateCustomer(custId);
        return ResponseEntity.ok(customer);
    }
}
