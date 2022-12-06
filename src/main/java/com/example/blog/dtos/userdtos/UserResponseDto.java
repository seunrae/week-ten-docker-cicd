package com.example.blog.dtos.userdtos;

import com.example.blog.dtos.postdtos.PostResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {
    private Long userId;
    private String userName;
    private String userEmail;
    //Contact -> Phone number
    private String userContact;
    private List<PostResponseDto> designs;

}
