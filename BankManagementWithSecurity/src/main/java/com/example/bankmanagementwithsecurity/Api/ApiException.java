package com.example.bankmanagementwithsecurity.Api;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}
