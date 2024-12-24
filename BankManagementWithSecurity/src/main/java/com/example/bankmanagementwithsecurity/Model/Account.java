package com.example.bankmanagementwithsecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "account number cannot be empty")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "Account number must be in the format XXXX-XXXX-XXXX-XXXX and contain only numbers")
    private String accountNumber;

    @NotNull(message = "balance cannot be empty")
    @Positive(message = "balance must be positive")
    private Double balance;

    //@AssertFalse
    private Boolean isActive = false;

    @ManyToOne
    @JsonIgnore
    private Customer customer;
}