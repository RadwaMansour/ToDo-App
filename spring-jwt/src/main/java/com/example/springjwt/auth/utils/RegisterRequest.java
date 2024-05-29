package com.example.springjwt.auth.utils;

import com.example.springjwt.auth.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String name;
    private String email;
    private String username;
    private String password;
    private boolean enabled;




}
