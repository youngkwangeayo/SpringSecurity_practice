package com.example.securitiesbasic.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    
    private String email;
    private String password;
    private LocalDateTime lgdate;
    private String role;
    private String username;
}
