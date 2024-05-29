package com.example.springjwt.auth.repositories;


import com.example.springjwt.auth.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String username);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2 where u.email = ?1")
    void updatePassword(String email, String password);
    @Transactional
    @Modifying
    @Query("DELETE FROM User u where u.password = ?2 and u.username = ?1")
    void deleteUser(String username, String password);

}
