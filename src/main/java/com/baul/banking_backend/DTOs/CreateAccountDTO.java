package com.baul.banking_backend.DTOs;

import com.baul.banking_backend.enums.Enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountDTO {
    @NotNull
    private AccountType accountType;
}
