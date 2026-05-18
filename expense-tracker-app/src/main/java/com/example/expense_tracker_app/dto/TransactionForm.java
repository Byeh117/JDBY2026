package com.example.expense_tracker_app.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionForm {

    private Long id;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be at least 0.01")
    private BigDecimal amount;

    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date must not be in the past or present")
    private LocalDate occurredOn;

    @Size(max = 255, message = "Note cannot exceed 255 characters")
    private String note;

    @NotNull(message = "Account cannot be null")
    private Long accountId;

    @NotNull(message = "Category cannot be null")
    private Long categoryId;
}
