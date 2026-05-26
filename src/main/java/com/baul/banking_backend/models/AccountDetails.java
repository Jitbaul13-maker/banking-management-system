package com.baul.banking_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int accountId;
    private int accountBalance;
    private boolean isActive;

    @OneToOne
    private Customer customer;
}
