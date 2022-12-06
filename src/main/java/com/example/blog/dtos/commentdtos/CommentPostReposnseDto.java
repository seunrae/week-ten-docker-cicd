package com.example.blog.dtos.commentdtos;

import com.example.blog.dtos.customerdtos.CustomerResponseDto;
import lombok.Data;

@Data
public class CommentPostReposnseDto {
    private CustomerResponseDto customer;
    private String commentText;
}
