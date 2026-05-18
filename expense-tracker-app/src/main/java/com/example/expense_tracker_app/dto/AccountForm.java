package com.example.expense_tracker_app.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountForm {

    private Long id;

    @NotBlank(message = "Please insert a valid name")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Type cannot be null")
    private String type;

    @NotNull(message = "Opening balance cannot be 0.00")
    @DecimalMin(value = "0.00", message="Opening balance must be at least 0.00")
    private BigDecimal openingBalance;
}
