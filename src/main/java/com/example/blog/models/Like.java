package com.example.blog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "like_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false, unique = true)
    private Long likeId;

    @Column(name = "customer_likes", nullable = false)
    private int LikeCount;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "design_id")
    private Post likePost;
}
