package com.baul.banking_backend.models;

import com.baul.banking_backend.enums.Enums.DepositType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepositDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer depositId;

    @Enumerated(EnumType.STRING)
    private DepositType depositType;

    private BigDecimal depositValue;

    private BigDecimal interest;

    private Integer tenureMonths;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate maturityDate;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

}
