package com.baul.banking_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer custId;
    private String custName;
    private Integer custAge;
    @Column(unique = true)
    private String custEmail;
    private String password;
    private Boolean active;

    @OneToMany(mappedBy = "customer")
    private List<AccountDetails> accountDetail = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<DepositDetails> depositDetails = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<CardDetails> cardDetails = new ArrayList<>();
}
