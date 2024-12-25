package com.example.bankmanagementwithsecurity.Service;

import com.example.bankmanagementwithsecurity.Api.ApiException;
import com.example.bankmanagementwithsecurity.DTO.CustomerInDTO;
import com.example.bankmanagementwithsecurity.Model.Account;
import com.example.bankmanagementwithsecurity.Model.Customer;
import com.example.bankmanagementwithsecurity.Model.MyUser;
import com.example.bankmanagementwithsecurity.Repository.AccountRepository;
import com.example.bankmanagementwithsecurity.Repository.AuthRepository;
import com.example.bankmanagementwithsecurity.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public void register(CustomerInDTO customerDTO) {
        MyUser customerUser = new MyUser();

        Customer customer = new Customer(null,
                customerDTO.getPhoneNumber(),
                customerUser, null);

        customerUser.setRole("CUSTOMER");
        customerUser.setUsername(customerDTO.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        customerUser.setPassword(hashPassword);
        customerUser.setName(customerDTO.getName());
        customerUser.setEmail(customerDTO.getEmail());

        authRepository.save(customerUser);
        customerRepository.save(customer);
    }

    public void updateCustomer(Integer id, CustomerInDTO customerInDTO) {
        Customer oldCustomer = customerRepository.findCustomerById(id);
        MyUser customerUser = authRepository.findMyUserById(id);
        if (oldCustomer == null || customerUser == null) {
            throw new ApiException("customer not found");
        }
        customerUser.setUsername(customerInDTO.getUsername());
        customerUser.setPassword(customerInDTO.getPassword());
        customerUser.setName(customerInDTO.getName());
        customerUser.setEmail(customerInDTO.getEmail());
        authRepository.save(customerUser);

        oldCustomer.setPhoneNumber(customerInDTO.getPhoneNumber());
        customerRepository.save(oldCustomer);
    }


    public void  deleteCustomer(Integer customer_id){
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("customer not found");
        }
        customerRepository.delete(customer);
    }

    public void deposit(Integer customer_id, Integer account_id, Double amount) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        Account account = accountRepository.findAccountById(account_id);
        if (customer == null) {
            throw new ApiException("customer not found");
        }
        if (account == null) {
            throw new ApiException("account not found");
        }
        if (!(account.getCustomer().getId().equals(customer.getId()))) {
            throw new ApiException("this customer cannot deposit to this account");
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdraw(Integer customer_id, Integer account_id, Double amount) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        Account account = accountRepository.findAccountById(account_id);
        if (customer == null) {
            throw new ApiException("customer not found");
        }
        if (account == null) {
            throw new ApiException("account not found");
        }
        if (!(account.getCustomer().getId().equals(customer.getId()))) {
            throw new ApiException("this customer cannot withdraw from this account");
        }
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);
        } else {
            throw new ApiException("balance not enough");
        }
    }

    public void transferFunds(Integer customer_id, Integer account1_id, Integer account2_id, Double amount) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        Account account1 = accountRepository.findAccountById(account1_id);
        Account account2 = accountRepository.findAccountById(account2_id);
        if (customer == null) {
            throw new ApiException("customer not found");
        }
        if (account1 == null || account2 == null) {
            throw new ApiException("account not found");
        }
        if (!(account1.getCustomer().getId().equals(customer.getId()))) {
            throw new ApiException("this customer cannot transfer from this account");
        }
        if (account1.getBalance() >= amount) {
            account1.setBalance(account1.getBalance() - amount);
            account2.setBalance(account2.getBalance() + amount);
            accountRepository.save(account1);
            accountRepository.save(account2);
        } else {
            throw new ApiException("balance not enough to transfer");
        }
    }

}
