package com.example.springjwt.auth.services;

import com.example.springjwt.auth.entities.User;
import com.example.springjwt.auth.repositories.UserRepository;
import com.example.springjwt.auth.utils.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;
    @Override
    public User insert(RegisterRequest registerRequest) {

        return authService.register(registerRequest);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found!"));
    }


    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);

    }

    @Override
    public void deleteByUser(String username, String password) {
        userRepository.deleteUser(username,password);
    }

    @Override
    public User update(User user, int id) {
        User userOld=new User();
        userOld=userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found!"));
        userOld.setName(user.getName());
        userOld.setEmail(user.getEmail());
        userOld.setUsername(user.getUsername());
        userOld.setRole(user.getRole());
        userOld.setPassword(user.getPassword());
        userOld.setEnabled(user.isEnabled());
        userOld.setJwt(userOld.getJwt());
        userOld.setForgotPassword(userOld.getForgotPassword());
        return userRepository.save(userOld);
    }

}
