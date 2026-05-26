package com.baul.banking_backend.models;

import com.baul.banking_backend.enums.Enums.AccountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer accountId;

    private BigDecimal accountBalance;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    private Boolean isActive;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "accountDetails", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private CardDetails card;
}
