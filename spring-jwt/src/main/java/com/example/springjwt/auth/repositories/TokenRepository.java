package com.example.springjwt.auth.repositories;


import com.example.springjwt.auth.entities.Jwt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Jwt, String> {

    Jwt findByToken(String Token);
}
