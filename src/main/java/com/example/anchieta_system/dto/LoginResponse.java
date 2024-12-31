package com.example.anchieta_system.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String email; // Changed from username to email to match User entity
} 