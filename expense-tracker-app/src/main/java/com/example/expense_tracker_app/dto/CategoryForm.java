package com.example.expense_tracker_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryForm {

    private Long id;

    @NotBlank(message = "Enter a valid Category Name")
    @Size(max = 80, message = "Category name cannot exceed 80 characters")
    private String name;

    @NotNull(message = "Type cannot be null")
    private String type;
}
