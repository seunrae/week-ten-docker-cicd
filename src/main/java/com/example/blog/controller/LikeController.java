package com.example.blog.controller;

import com.example.blog.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    @GetMapping("/create-like/{likeCount}/{customerId}/{postId}")
    public ResponseEntity<String> createLike(@PathVariable int likeCount, @PathVariable  Long customerId, @PathVariable Long postId){
        String newLike = likeService.createLike(likeCount,customerId,postId);
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }
    @DeleteMapping("/unlike/{likeId}")
    public ResponseEntity<String> unlikePost(@PathVariable Long likeId){
        String unlike= likeService.unLike(likeId);
        return new ResponseEntity<>(unlike, HttpStatus.OK);
    }
}
