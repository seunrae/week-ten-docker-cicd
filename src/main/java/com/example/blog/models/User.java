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
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    //Fashion designer i.e Dele
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    //Contact -> Phone number
    @Column(name = "user_phone", nullable = false)
    private String userContact;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> userDesigns;

}
