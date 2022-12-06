package com.example.blog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "customer_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, unique = true)
    private Long customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerUserName;

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(name = "customer_password", nullable = false)
    private String customerPassword;

    @Column(name = "customer_phone", nullable = false)
    private String customerPhoneNumber;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "commentAuthor" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> customerComments;

}
