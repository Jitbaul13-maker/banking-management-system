package com.baul.banking_backend.models;

import com.baul.banking_backend.enums.Enums.DepositType;
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
public class DepositDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer depositId;

    @Enumerated(EnumType.STRING)
    private DepositType depositType;

    private BigDecimal depositValue;

    private Float interest;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime issueDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(nullable = true)
    private LocalDateTime maturityDate;

    private Boolean active;

    @ManyToOne
    private Customer customer;

}
