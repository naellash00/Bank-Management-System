package com.example.bankmanagementwithsecurity.Controller;

import com.example.bankmanagementwithsecurity.Api.ApiResponse;
import com.example.bankmanagementwithsecurity.DTO.EmployeeInDTO;
import com.example.bankmanagementwithsecurity.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity register(EmployeeInDTO employeeInDTO){
        employeeService.register(employeeInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("registered successfully"));
    }
}
