package com.example.blog.dtos.commentdtos;

import com.example.blog.dtos.customerdtos.CustomerResponseDto;
import com.example.blog.dtos.postdtos.PostCommentResponseDto;
import com.example.blog.dtos.postdtos.PostResponseDto;
import com.example.blog.models.Customer;
import com.example.blog.models.Post;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
public class CommentResponseDto {
    private String commentText;
    private Date createdAt;
    private CustomerResponseDto customer;
    private PostCommentResponseDto post;
}
