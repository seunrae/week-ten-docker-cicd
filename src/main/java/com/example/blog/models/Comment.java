package com.example.blog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="comment_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, unique = true)
    private Long commentId;

    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer commentAuthor;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "design_id")
    private Post post;

}
