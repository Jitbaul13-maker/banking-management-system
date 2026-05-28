package com.baul.banking_backend.models;

import com.baul.banking_backend.enums.Enums.CardType;
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
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer cardId;

    private String maskedId;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime issueDate;

    private Integer tenure;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime expiryDate;

    private Boolean active;

    @ManyToOne
    private Customer customer;
}