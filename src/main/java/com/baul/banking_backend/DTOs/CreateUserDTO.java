package com.baul.banking_backend.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    @NotBlank
    private String custName;

    @NotNull
    @Min(18)
    private Integer custAge;

    @Email
    @NotBlank
    private String custEmail;

    @NotBlank
    @Size(max = 21, min = 12)
    private String password;
}
