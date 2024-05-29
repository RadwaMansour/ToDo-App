package com.example.springjwt.auth.services;


import com.example.springjwt.auth.entities.Jwt;
import com.example.springjwt.auth.entities.User;
import com.example.springjwt.auth.repositories.TokenRepository;
import com.example.springjwt.auth.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    private final UserRepository userRepository;
    
    private final TokenRepository tokenRepository;

    public TokenService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    public Jwt createToken(String username) {
        User user = userRepository.findByEmail(username);

        Jwt jwt = user.getJwt();

        if (jwt == null) {
            long tokenValidity = 30 * 20 * 1000;
            jwt = Jwt.builder()
                    .token(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(tokenValidity))
                    .createdTime(Instant.now())
                    .user(user)
                    .build();

            tokenRepository.save(jwt);
        }

        return jwt;
    }

    public Jwt checkToken(String Token) {
        Jwt token = tokenRepository.findByToken(Token);

        if (token.getExpirationTime().compareTo(Instant.now()) < 0) {
            tokenRepository.delete(token);
            throw new RuntimeException("Refresh Token expired");
        }

        return token;
    }
    public Jwt getToken(String Token) {
        Jwt token = tokenRepository.findByToken(Token);
        System.out.println("yesssssssssssssssssssss");

        return token;
    }
}
