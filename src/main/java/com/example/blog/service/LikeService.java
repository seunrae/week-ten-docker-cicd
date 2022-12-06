package com.example.blog.service;

import com.example.blog.models.Customer;
import com.example.blog.models.Like;
import com.example.blog.models.Post;
import com.example.blog.repositories.CustomerRepository;
import com.example.blog.repositories.LikeRepository;
import com.example.blog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final CustomerRepository customerRepository;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository, CustomerRepository customerRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.customerRepository = customerRepository;
        this.postRepository = postRepository;
    }

    public String createLike(int likeCount, Long customerId, Long postId){
        Customer customer = customerRepository.findById(customerId).get();
        Post post = postRepository.findById(postId).get();
        Like like = new Like();
        if(likeCount != 1) {
            return "enter 1 to like";
        }
        else if(likeRepository.existsByCustomerAndAndLikePost(customer, post) == true){
            return "Post already liked";
        }
        else {
            like.setLikeCount(likeCount);
            like.setCustomer(customer);
            like.setLikePost(post);
            likeRepository.save(like);
        }

        return "post liked <3";
    }

    public String unLike(Long likeId){
        likeRepository.deleteById(likeId);
        return "post unliked :(";
    }
}
