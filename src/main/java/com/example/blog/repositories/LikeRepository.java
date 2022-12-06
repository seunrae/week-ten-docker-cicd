package com.example.blog.repositories;

import com.example.blog.models.Customer;
import com.example.blog.models.Like;
import com.example.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
     Boolean existsByCustomerAndAndLikePost(Customer customerId, Post postId);
}
