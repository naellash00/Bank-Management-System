package com.example.bankmanagementwithsecurity.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeInDTO {

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 4, max = 10, message = "username must be between 4 - 10 characters")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, max = 20, message = "name must be between 2 - 20 characters")
    private String name;

    @NotEmpty(message = "email cannot be empty")
    @Email(message = "enter a valid email")
    private String email;

    @NotEmpty(message = "position cannot be null")
    private String position;

    @NotNull(message = "salary cannot be empty")
    @Positive(message = "salary cannot be negative")
    private Integer salary;
}
