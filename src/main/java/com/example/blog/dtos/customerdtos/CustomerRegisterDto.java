package com.example.blog.dtos.customerdtos;

import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Data
public class CustomerRegisterDto {
    private String customerUserName;
    private String customerEmail;
    private String customerPassword;
    private String customerPhoneNumber;
}
