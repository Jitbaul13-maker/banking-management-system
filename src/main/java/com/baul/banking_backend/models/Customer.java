package com.baul.banking_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int custId;
    @Column(unique = true)
    private String custName;
    private int custAge;
    private String custEmail;
    @Column(unique = true)
    private String password;
    private boolean isActive;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "accountdetails")
    private AccountDetails accountDetails;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "carddetails")
    private CardDetails cardDetails;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "carddetails")
    private DepositDetails depositDetails;
}
