package com.example.bankmanagementwithsecurity.Controller;

import com.example.bankmanagementwithsecurity.Api.ApiResponse;
import com.example.bankmanagementwithsecurity.DTO.CustomerInDTO;
import com.example.bankmanagementwithsecurity.Model.MyUser;
import com.example.bankmanagementwithsecurity.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CustomerInDTO customerInDTO){
        customerService.register(customerInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("registered successfully"));
    }

    @PutMapping("/deposit/{account_id}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer account_id, @PathVariable Double amount){
        customerService.deposit(myUser.getId(), account_id, amount);
        return ResponseEntity.status(200).body(new ApiResponse("deposit completed successfully"));
    }

    @PutMapping("/withdraw/{account_id}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer account_id, @PathVariable Double amount){
        customerService.withdraw(myUser.getId(), account_id, amount);
        return ResponseEntity.status(200).body(new ApiResponse("withdraw completed successfully"));
    }

    @PutMapping("/transfer/{account1_id}/{account2_id}/{amount}")
    public ResponseEntity transferFunds(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer account1_id, @PathVariable Integer account2_id, @PathVariable Double amount){
        customerService.transferFunds(myUser.getId(), account1_id, account2_id, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Transfer completed successfully"));
    }
}
