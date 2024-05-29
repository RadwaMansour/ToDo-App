package com.example.springjwt.auth.services;

import com.example.springjwt.auth.entities.User;
import com.example.springjwt.auth.utils.RegisterRequest;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public interface UserService {
    User insert(RegisterRequest registerRequest) ;


    User findById(int id);
    void deleteById(int id);
    void deleteByUser(String username, String password);
    User update(User user,int id);

}
