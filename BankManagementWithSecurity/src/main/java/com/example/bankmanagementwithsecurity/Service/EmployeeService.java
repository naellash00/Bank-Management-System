package com.example.bankmanagementwithsecurity.Service;

import com.example.bankmanagementwithsecurity.Api.ApiException;
import com.example.bankmanagementwithsecurity.DTO.CustomerInDTO;
import com.example.bankmanagementwithsecurity.DTO.EmployeeInDTO;
import com.example.bankmanagementwithsecurity.Model.Customer;
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

    public void register(EmployeeInDTO employeeDTO) {
        MyUser employeeUser = new MyUser();

        employeeUser.setRole("EMPLOYEE");
        employeeUser.setUsername(employeeDTO.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        employeeUser.setPassword(hashPassword);
        employeeUser.setName(employeeDTO.getName());
        employeeUser.setEmail(employeeDTO.getEmail());

        authRepository.save(employeeUser);

        Employee employee = new Employee(null,
                employeeDTO.getPosition(),
                employeeDTO.getSalary(),
                employeeUser);
        employee.setUser(employeeUser);

        employeeRepository.save(employee);
    }

    public void updateEmployee(Integer id, EmployeeInDTO employeeInDTO) {
        Employee oldEmployee = employeeRepository.findEmployeeById(id);
        MyUser customerEmployee = authRepository.findMyUserById(id);
        if (oldEmployee == null || customerEmployee == null) {
            throw new ApiException("customer not found");
        }
        customerEmployee.setUsername(employeeInDTO.getUsername());
        customerEmployee.setPassword(employeeInDTO.getPassword());
        customerEmployee.setName(employeeInDTO.getName());
        customerEmployee.setEmail(employeeInDTO.getEmail());
        authRepository.save(customerEmployee);

        oldEmployee.setPosition(employeeInDTO.getPosition());
        oldEmployee.setSalary(employeeInDTO.getSalary());
        employeeRepository.save(oldEmployee);
    }

    public void deleteEmployee(Integer employee_id) {
        Employee employee = employeeRepository.findEmployeeById(employee_id);
        if (employee == null) {
            throw new ApiException("customer not found");
        }
        employeeRepository.delete(employee);
    }


}
