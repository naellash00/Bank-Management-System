package com.example.bankmanagementwithsecurity.Controller;

import com.example.bankmanagementwithsecurity.Api.ApiResponse;
import com.example.bankmanagementwithsecurity.DTO.EmployeeInDTO;
import com.example.bankmanagementwithsecurity.Model.MyUser;
import com.example.bankmanagementwithsecurity.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid EmployeeInDTO employeeInDTO){
        employeeService.updateEmployee(myUser.getId(), employeeInDTO);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal MyUser myUser){
        employeeService.deleteEmployee(myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
    }

}
