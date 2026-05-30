package com.baul.banking_backend.models;

import com.baul.banking_backend.enums.Enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer custId;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    private String custName;

    private Integer custAge;

    @Column(unique = true)
    private String custEmail;

    @JsonIgnore
    private String password;

    private Boolean active;

    @OneToMany(mappedBy = "customer")
    private List<AccountDetails> accountDetail = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<DepositDetails> depositDetails = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<CardDetails> cardDetails = new ArrayList<>();
}
