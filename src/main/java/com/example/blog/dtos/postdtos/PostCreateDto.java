package com.example.blog.dtos.postdtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDto {
    private String designTitle;
    private String designDescription;
}
