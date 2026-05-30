package com.baul.banking_backend.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    private String custName;

    @Min(18)
    private Integer custAge;

    @Email
    private String custEmail;

    @Size(max = 21, min = 12)
    private String password;
}
