package com.example.bankmanagementwithsecurity.Repository;

import com.example.bankmanagementwithsecurity.Model.Account;
import com.example.bankmanagementwithsecurity.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);
    List<Account> findAccountByCustomer(Customer customer);
}
