package com.baul.banking_backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int depositId;
    private String depositType;
    private int depositValue;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime issueDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime maturityDate;
    private Boolean isActive;

    @ManyToOne
    private Customer customer;

}
