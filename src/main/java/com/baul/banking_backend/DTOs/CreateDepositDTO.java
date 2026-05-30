package com.baul.banking_backend.DTOs;

import com.baul.banking_backend.enums.Enums.DepositType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepositDTO {
    @NotNull
    private DepositType depositType;

    @NotNull
    @DecimalMin("100000")
    private BigDecimal depositValue;

    @NotNull
    private BigDecimal interest;

    @NotNull
    @Positive
    private Integer tenureMonths;

    @NotNull
    private LocalDate issueDate;
}
