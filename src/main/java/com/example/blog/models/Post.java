package com.example.blog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "post_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_id", nullable = false, unique = true)
    private Long designId;

    @Column(name = "design_title", nullable = false)
    private String designTitle;

    @Column(name = "design_description", nullable = false)
    private String designDescription;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updateAt;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> customerComments;

    @OneToMany(mappedBy = "likePost" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes;


}
