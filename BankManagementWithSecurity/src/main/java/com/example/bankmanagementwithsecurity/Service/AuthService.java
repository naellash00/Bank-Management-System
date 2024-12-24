package com.example.bankmanagementwithsecurity.Service;

import com.example.bankmanagementwithsecurity.Model.MyUser;
import com.example.bankmanagementwithsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    // register for customer
//    public void register(MyUser myUser){
//        // set the role and the hashed password
//        myUser.setRole("CUSTOMER");
//        String hashPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
//        myUser.setPassword(hashPassword);
//        authRepository.save(myUser);
//    }

    // register for employee
//    public void register(MyUser myUser){
//
//    }

}
