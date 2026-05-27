package com.baul.banking_backend.models;

import com.baul.banking_backend.enums.Enums.AccountType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer accountId;

    private BigDecimal accountBalance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "accountDetails", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    fetch = FetchType.LAZY)
    private List<CardDetails> cards = new ArrayList<>();
}
