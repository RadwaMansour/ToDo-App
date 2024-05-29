package com.example.springjwt.auth.services;


import com.example.springjwt.auth.entities.ForgotPassword;
import com.example.springjwt.auth.entities.User;
import com.example.springjwt.auth.entities.UserRole;
import com.example.springjwt.auth.repositories.UserRepository;
import com.example.springjwt.auth.utils.ActiveRequest;
import com.example.springjwt.auth.utils.AuthResponse;
import com.example.springjwt.auth.utils.LoginRequest;
import com.example.springjwt.auth.utils.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterRequest registerRequest) {
        var user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .enabled(false)
                .role(UserRole.USER)
                .build();

        User savedUser = userRepository.save(user);
  //      var accessToken = jwtService.generateToken(savedUser);
   //     var token = tokenService.createToken(savedUser.getEmail());

        return savedUser;
    }

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                        )
        );

        var user = userRepository.findByEmail(loginRequest.getEmail());
        Map<String , Object> extraClaims = new HashMap<>();
        var accessToken = jwtService.createToken(user,extraClaims);
        var token = tokenService.createToken(loginRequest.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .Token(token.getToken())
                .build();
    }

}
