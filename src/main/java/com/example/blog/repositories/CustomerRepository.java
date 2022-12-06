package com.example.blog.repositories;

import com.example.blog.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCustomerEmailAndCustomerPassword(String userName, String password);
}
