package com.example.bankmanagementwithsecurity.Controller;

import com.example.bankmanagementwithsecurity.Api.ApiResponse;
import com.example.bankmanagementwithsecurity.Model.MyUser;
import com.example.bankmanagementwithsecurity.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody @Valid MyUser myUser){
//        authService.register(myUser);
//        return ResponseEntity.status(200).body(new ApiResponse("registered successfully"));
//    }

}
