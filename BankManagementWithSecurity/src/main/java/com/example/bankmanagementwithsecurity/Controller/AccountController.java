package com.example.bankmanagementwithsecurity.Controller;

import com.example.bankmanagementwithsecurity.Api.ApiResponse;
import com.example.bankmanagementwithsecurity.Model.Account;
import com.example.bankmanagementwithsecurity.Model.MyUser;
import com.example.bankmanagementwithsecurity.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create/bank-account")
    public ResponseEntity createBankAccount(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid Account account){
        accountService.createBankAccount(myUser.getId(), account);
        return ResponseEntity.status(200).body(new ApiResponse("Account created Successfully"));
    }

    @PutMapping("/activate-account/{account_id}")
    public ResponseEntity activateAccount(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer account_id){
        accountService.activateAccount(myUser.getId(), account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Account Activated Successfully"));
    }

    @GetMapping("/get/account-details/{account_id}")
    public ResponseEntity getAccountDetails(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer account_id){
        return ResponseEntity.status(200).body(accountService.getAccountDetails(myUser.getId(), account_id));
    }

    @GetMapping("/get/customer/accounts")
    public ResponseEntity getCustomerAccounts(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(accountService.getCustomerAccounts(myUser.getId()));
    }

    @PutMapping("/block-account/{account_id}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal MyUser myUser, @PathVariable Integer account_id){
        accountService.blockAccount(account_id);
        return ResponseEntity.status(200).body(new ApiResponse("account bocked successfully"));
    }
}
