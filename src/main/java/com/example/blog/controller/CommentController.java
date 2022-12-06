package com.example.blog.controller;

import com.example.blog.dtos.commentdtos.CommentCreateDto;
import com.example.blog.dtos.commentdtos.CommentResponseDto;
import com.example.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/create-comment/{customerId}/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentCreateDto commentCreateDto, @PathVariable Long customerId, @PathVariable Long postId){
        CommentResponseDto newComment = commentService.createComment(commentCreateDto,customerId,postId);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
    @PatchMapping("/update-comment/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody CommentCreateDto commentCreateDto,@PathVariable Long commentId){
        CommentResponseDto updComment = commentService.updateComment(commentCreateDto,commentId);
        return new ResponseEntity<>(updComment, HttpStatus.OK);
    }

    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId){
        String delComment = commentService.deleteComment(commentId);
        return new ResponseEntity<>(delComment, HttpStatus.OK);
    }

    @GetMapping("/show-comments")
    public ResponseEntity<List<CommentResponseDto>> showAllComments(){
      List<CommentResponseDto> comments = commentService.showComments();
      return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/show-comment/{commentId}")
    public ResponseEntity<CommentResponseDto> showComment(@PathVariable Long commentId){
        CommentResponseDto comment = commentService.showComment(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
