package com.example.banking_transfer_api.dto.account;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TransferRequest {

    private Long id;

    @NotBlank(message = "Account number cannot be blank")
    private String toAccountNumber;

    @NotNull(message = "Transfer amount must exceed 0.00")
    @DecimalMin(value = "0.01", message = "Transfer amount must be greater than 0.00")
    private BigDecimal amount;

    @Size(max = 255, message = "Note cannot exceed 255 characters")
    private String description;


}
