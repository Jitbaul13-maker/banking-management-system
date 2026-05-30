package com.baul.banking_backend.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionDTO {

    @NotNull
    @DecimalMin("1.00")
    private BigDecimal amount;
}
