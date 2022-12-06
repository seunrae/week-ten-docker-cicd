package com.example.blog.dtos.userdtos;

import lombok.Data;

@Data
public class UserLoginDto {
    private String userEmail;
    private String userPassword;
}
