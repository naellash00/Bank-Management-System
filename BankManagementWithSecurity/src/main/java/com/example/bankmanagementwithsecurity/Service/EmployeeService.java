package com.example.bankmanagementwithsecurity.Service;

import com.example.bankmanagementwithsecurity.DTO.EmployeeInDTO;
import com.example.bankmanagementwithsecurity.Model.Employee;
import com.example.bankmanagementwithsecurity.Model.MyUser;
import com.example.bankmanagementwithsecurity.Repository.AuthRepository;
import com.example.bankmanagementwithsecurity.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;

//    public void register(EmployeeInDTO employeeDTO){
//        MyUser employeeUser = new MyUser();
//        Employee employee = new Employee(null,
//                employeeDTO.getUsername(),
//                employeeDTO.getPassword(),
//                employeeDTO.getName(),
//                employeeDTO.getEmail(),
//                employeeDTO.getPosition(),
//                employeeDTO.getSalary(),
//                employeeUser);
//
//        employeeUser.setRole("EMPLOYEE");
//        employeeUser.setUsername(employee.getUsername());
////        String hashPassword = new BCryptPasswordEncoder().encode(employee.getPassword());
////        employeeUser.setPassword(hashPassword);
//        employeeUser.setPassword(employee.getPassword());
//
//        employeeRepository.save(employee);
//        authRepository.save(employeeUser);
//    }

    public void register(EmployeeInDTO employeeDTO) {
        MyUser employeeUser = new MyUser();

        Employee employee = new Employee(null,
                employeeDTO.getPosition(),
                employeeDTO.getSalary(),
                employeeUser);

        employeeUser.setRole("EMPLOYEE");
        employeeUser.setUsername(employeeDTO.getUsername());
//        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
//        employeeUser.setPassword(hashPassword);
        employeeUser.setPassword(employeeDTO.getPassword());
        employeeUser.setName(employeeDTO.getName());
        employeeUser.setEmail(employeeDTO.getEmail());

        authRepository.save(employeeUser);
        employeeRepository.save(employee);
    }


}
