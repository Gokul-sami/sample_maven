package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Login;
import com.example.demo.repository.LoginRepo;

@Service
public class LoginService {
    @Autowired
    private LoginRepo repo;

    public Boolean log(String username, String password){
        Login result = repo.findByUsernameAndPassword(username, password);
        if(result != null){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean signUp(String username, String password){
        Login newUser = new Login(username, password);
        Login result = repo.save(newUser);
        if(result != null){
            return true;
        }
        else{
            return false;
        }
    }
}
