package com.example.bankmanagementwithsecurity.Service;

import com.example.bankmanagementwithsecurity.Api.ApiException;
import com.example.bankmanagementwithsecurity.Model.MyUser;
import com.example.bankmanagementwithsecurity.Repository.AuthRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

    // re-implement the method in UserDetailsService to access my user username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = authRepository.findMyUserByUsername(username);
        if(myUser == null){
            throw new ApiException("username or password is wrong");
        }
        System.out.println("Attempting to load user with username: " + username);
        return myUser;
    }
}















