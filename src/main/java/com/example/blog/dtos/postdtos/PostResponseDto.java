package com.example.blog.dtos.postdtos;

import com.example.blog.dtos.commentdtos.CommentPostReposnseDto;
import com.example.blog.dtos.userdtos.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    private Long designId;
    private String designTitle;
    private String designDescription;
    private Date createdAt;
    private UserResponseDto user;
    private int likes;
    private List<CommentPostReposnseDto> comments;
}
