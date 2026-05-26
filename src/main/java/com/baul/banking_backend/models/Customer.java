package com.baul.banking_backend.models;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "customer")
    private AccountDetails accountDetails;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "customer")
    private List<DepositDetails> depositDetails;
}
