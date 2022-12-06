package com.example.blog.repositories;

import com.example.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserEmailAndUserPassword(String userEmail, String userPassword);


}
