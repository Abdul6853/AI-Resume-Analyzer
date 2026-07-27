package com.example.ResumeAnalyxer.DTOs;

import lombok.Data;

@Data
public class LoginResponse
{
    private int userId;
    private String email;
    private String name;
    private String message;
    private String token;
}
