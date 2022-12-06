package com.example.blog.repositories;

import com.example.blog.dtos.userdtos.UserResponseDto;
import com.example.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByUser(UserResponseDto userId);
    List<Post> findAllByDesignTitle(String title);
    List<Post> findAllByCustomerComments(String comments);
}
