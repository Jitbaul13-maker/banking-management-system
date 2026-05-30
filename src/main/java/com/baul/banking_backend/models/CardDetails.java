package com.baul.banking_backend.models;

import com.baul.banking_backend.enums.Enums.CardType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate issueDate;

    private Integer tenure;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expiryDate;

    private Boolean active;

    @ManyToOne
    private User customer;
}