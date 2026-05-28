package com.example.banking_transfer_api.dto.account;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class WithdrawRequest {

    @NotNull(message = "Withdraw amount cannot be below 0.01")
    @DecimalMin(value = "0.01", message = "Withdraw amount must be above 0.01")
    private BigDecimal amount;
}
