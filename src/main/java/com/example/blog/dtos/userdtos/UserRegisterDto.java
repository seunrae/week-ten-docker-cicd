package com.example.blog.dtos.userdtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRegisterDto {
    private String userName;
    private String userEmail;
    private String userPassword;
    //Contact -> Phone number
    private String userContact;
}
