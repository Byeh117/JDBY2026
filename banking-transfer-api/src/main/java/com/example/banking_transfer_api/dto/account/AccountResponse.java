package com.example.banking_transfer_api.dto.account;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AccountResponse {
    private Long id;
    private String accountNumber;
    private BigDecimal balance;
    private String status;
    private LocalDateTime createdAt;
}
