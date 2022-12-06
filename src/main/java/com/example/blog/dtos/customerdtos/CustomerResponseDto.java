package com.example.blog.dtos.customerdtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CustomerResponseDto {
    private String customerUserName;
    private String customerEmail;
    private String customerPhoneNumber;
}
