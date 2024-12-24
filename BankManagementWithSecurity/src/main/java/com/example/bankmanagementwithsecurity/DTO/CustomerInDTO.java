package com.example.bankmanagementwithsecurity.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerInDTO {

    @NotEmpty(message = "username cannot be empty")
    @Size(min = 4, max = 10, message = "username must be between 4 - 10 characters")
    private String username;

    @NotEmpty(message = "password cannot be empty")
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;

    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, max = 20, message = "name must be between 2 - 20 characters")
    private String name;

//    @NotEmpty(message = "email cannot be empty")
//    @Email(message = "enter a valid email")
    private String email;

    @NotEmpty(message = "phone number cannot be empty")
    @Pattern(regexp ="^05\\d{8}$", message = "phone number must start with '05' and be 10 digits")
    private String phoneNumber;
}
