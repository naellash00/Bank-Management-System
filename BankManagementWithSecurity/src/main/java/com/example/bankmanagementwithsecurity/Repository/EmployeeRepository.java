package com.example.bankmanagementwithsecurity.Repository;

import com.example.bankmanagementwithsecurity.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeById(Integer id);
}
