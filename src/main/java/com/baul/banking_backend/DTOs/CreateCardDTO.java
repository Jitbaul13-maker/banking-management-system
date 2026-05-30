package com.baul.banking_backend.DTOs;

import com.baul.banking_backend.enums.Enums.CardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardDTO {

    @NotBlank
    private String maskedId;

    @NotNull
    private CardType cardType;

    @NotNull
    private LocalDate issueDate;

    @NotNull
    @Positive
    private Integer tenure;
}
