package com.example.springjwt.auth.controllers;


import com.example.springjwt.auth.entities.ForgotPassword;
import com.example.springjwt.auth.entities.Jwt;
import com.example.springjwt.auth.entities.MailBody;
import com.example.springjwt.auth.entities.User;
import com.example.springjwt.auth.repositories.ForgotPasswordRepository;
import com.example.springjwt.auth.services.AuthService;
import com.example.springjwt.auth.services.EmailService;
import com.example.springjwt.auth.services.JwtService;
import com.example.springjwt.auth.services.TokenService;
import com.example.springjwt.auth.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController2 {

    private final AuthService authService;
    private final TokenService tokenService;
    private final JwtService jwtService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final EmailService emailService;
    @Autowired
    private UserDetailsService userDetailsService;
    public AuthController2(AuthService authService, TokenService tokenService, JwtService jwtService, ForgotPasswordRepository forgotPasswordRepository, EmailService emailService) {
        this.authService = authService;
        this.tokenService = tokenService;
        this.jwtService = jwtService;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        User user=authService.register(registerRequest);
        int otp = otpGenerator();
        ForgotPassword fp = ForgotPassword.builder()
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 30*20 * 1000))
                .user(user)
                .build();
        forgotPasswordRepository.save(fp);
        MailBody mailBody = MailBody.builder()
                .to(user.getEmail())
                .text("successful Registration")
                .subject("Complete Registration!")
                .build();
        emailService.sendSimpleMessage(mailBody);
        return ResponseEntity.ok("Register succesfull");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/checkToken/{token}")
    public ResponseEntity<String> checkToken(@PathVariable String token) {

        Jwt jwt = tokenService.getToken(token);
        System.out.println(jwt.toString());
        User user = jwt.getUser();
        System.out.println(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        boolean check=jwtService.isTokenValid(token,userDetails);
        System.out.println(check);
        if (check)
            return ResponseEntity.ok("token is valid");
        else
            return ResponseEntity.ok("token is not valid");
    }
    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

}
