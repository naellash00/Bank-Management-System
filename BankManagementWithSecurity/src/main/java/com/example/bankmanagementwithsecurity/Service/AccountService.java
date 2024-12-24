package com.example.bankmanagementwithsecurity.Service;

import com.example.bankmanagementwithsecurity.Api.ApiException;
import com.example.bankmanagementwithsecurity.Model.Account;
import com.example.bankmanagementwithsecurity.Model.Customer;
import com.example.bankmanagementwithsecurity.Repository.AccountRepository;
import com.example.bankmanagementwithsecurity.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public void createBankAccount(Integer customer_id, Account account) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("customer not found");
        }

        account.setCustomer(customer);
        //account.setIsActive(true);
        accountRepository.save(account);
    }

    // done by admin
    public void activateAccount(Integer customer_id, Integer account_id) {
        // check their id and check this customer is the same in the account
        //Customer customer = customerRepository.findCustomerById(customer_id);
        Account account = accountRepository.findAccountById(account_id);
//        if(customer == null){
//            throw new ApiException("customer not found");
//        }
        if (account == null) {
            throw new ApiException("account not found");
        }
//        if(!(account.getCustomer().getId().equals(customer.getId()))){
//            throw new ApiException("this customer cannot activate this account");
//        }
        account.setIsActive(true);
        accountRepository.save(account);
    }

    public Account getAccountDetails(Integer customer_id, Integer account_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        Account account = accountRepository.findAccountById(account_id);
        if (customer == null) {
            throw new ApiException("customer not found");
        }
        if (account == null) {
            throw new ApiException("account not found");
        }

        if (!(account.getCustomer().getId().equals(customer.getId()))) {
            throw new ApiException("this customer cannot view this account");
        }
        return account;
    }


    public List<Account> getCustomerAccounts(Integer customer_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("customer not found");
        }
        return accountRepository.findAccountByCustomer(customer);
    }

    public void blockAccount(Integer account_id) {
        Account account = accountRepository.findAccountById(account_id);
        if (account == null) {
            throw new ApiException("account not found");
        }
        account.setIsActive(false);
        accountRepository.save(account);
    }

}
