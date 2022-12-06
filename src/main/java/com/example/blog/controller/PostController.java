package com.example.blog.controller;

import com.example.blog.dtos.commentdtos.CommentPostReposnseDto;
import com.example.blog.dtos.commentdtos.CommentResponseDto;
import com.example.blog.dtos.postdtos.PostCreateDto;
import com.example.blog.dtos.postdtos.PostResponseDto;
import com.example.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping("/create-post/{userId}")
    private ResponseEntity<PostResponseDto> createPost(@RequestBody PostCreateDto postCreateDto, @PathVariable Long userId){
        PostResponseDto newPost = postService.createPost(postCreateDto, userId);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/show-posts")
    private ResponseEntity<List<PostResponseDto>> showAllPost(){
        List<PostResponseDto> posts = postService.showPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/show-post/{userId}")
    private ResponseEntity<PostResponseDto> showAllPost(@PathVariable Long userId){
        PostResponseDto post = postService.showPost(userId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PatchMapping("/update-post/{postId}")
    private ResponseEntity<PostResponseDto> updatePost(@RequestBody PostCreateDto postCreateDto,@PathVariable Long postId){
        PostResponseDto updtPost = postService.updatePost(postCreateDto,postId);
        return new ResponseEntity<>(updtPost, HttpStatus.ACCEPTED);
    }

    @GetMapping("/delete-post/{postId}")
    private ResponseEntity<String> deletePost(@PathVariable Long postId){
        String delPost = postService.deletePost(postId);
        return new ResponseEntity<>(delPost,HttpStatus.OK);
    }

    @GetMapping("/search-title")
    private ResponseEntity<List<PostResponseDto>> showPostBytitle(@RequestParam (value = "title") String title){
        List<PostResponseDto> postResponseDtos =  postService.showPostTitle(title);
        return new ResponseEntity<>(postResponseDtos,HttpStatus.OK);
    }

    @GetMapping("/search-comments")
    private ResponseEntity<List<CommentResponseDto>> showPostComment(@RequestParam (value = "comment") String comment){
        List<CommentResponseDto> postResponseDtos = postService.showPostComment(comment);
        return  new ResponseEntity<>(postResponseDtos,HttpStatus.OK);
    }
}
