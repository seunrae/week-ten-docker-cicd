package com.example.blog.dtos.customerdtos;

import lombok.Data;

@Data
public class CustomerLoginDto {
    private String customerEmail;
    private String customerPassword;
}
