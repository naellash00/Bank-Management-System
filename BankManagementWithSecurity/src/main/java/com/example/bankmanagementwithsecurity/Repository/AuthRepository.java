package com.example.bankmanagementwithsecurity.Repository;

import com.example.bankmanagementwithsecurity.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser, Integer> {
    MyUser findMyUserByUsername(String username);
}
