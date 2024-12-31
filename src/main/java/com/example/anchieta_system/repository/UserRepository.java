package com.example.anchieta_system.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.anchieta_system.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
} 