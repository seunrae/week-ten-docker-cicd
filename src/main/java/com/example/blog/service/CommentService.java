package com.example.blog.service;

import com.example.blog.dtos.commentdtos.CommentCreateDto;
import com.example.blog.dtos.commentdtos.CommentResponseDto;
import com.example.blog.dtos.customerdtos.CustomerResponseDto;
import com.example.blog.dtos.postdtos.PostCommentResponseDto;
import com.example.blog.dtos.postdtos.PostResponseDto;
import com.example.blog.models.Comment;
import com.example.blog.models.Customer;
import com.example.blog.models.Post;
import com.example.blog.repositories.CommentRepository;
import com.example.blog.repositories.CustomerRepository;
import com.example.blog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CustomerRepository customerRepository;
    private final PostRepository postRepository;
    public CommentService(CommentRepository commentRepository, CustomerRepository customerRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.customerRepository = customerRepository;
        this.postRepository = postRepository;
    }

    public CommentResponseDto createComment(CommentCreateDto commentCreateDto, Long customerId, Long postId){
        Customer customer = customerRepository.findById(customerId).get();
        Post post = postRepository.findById(postId).get();
        Comment comment = new Comment();
        comment.setCommentText(commentCreateDto.getCommentText());
        comment.setCommentAuthor(customer);
        comment.setPost(post);
        commentRepository.save(comment);
        getCustomer(customer);
        getPost(post);
        return getComment(comment);
    }

    public CustomerResponseDto getCustomer(Customer customer){
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setCustomerUserName(customer.getCustomerUserName());
        customerResponseDto.setCustomerEmail(customer.getCustomerEmail());
        customerResponseDto.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());

        return customerResponseDto;
    }

    public PostCommentResponseDto getPost(Post post){
        PostCommentResponseDto postCommentResponseDto = new PostCommentResponseDto();
        postCommentResponseDto.setDesignTitle(post.getDesignTitle());
        postCommentResponseDto.setDesignDescription(post.getDesignDescription());

        return postCommentResponseDto;
    }

    private CommentResponseDto getComment(Comment comment){
        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setCommentText(comment.getCommentText());
        commentResponseDto.setCreatedAt(comment.getCreatedAt());
        commentResponseDto.setCustomer(getCustomer(comment.getCommentAuthor()));
        commentResponseDto.setPost(getPost(comment.getPost()));

        return commentResponseDto;
    }

    public CommentResponseDto updateComment(CommentCreateDto commentCreateDto, Long commentId){
        Comment comment = commentRepository.findById(commentId).get();
        comment.setCommentText(commentCreateDto.getCommentText());
        commentRepository.save(comment);
        return getComment(comment);
    }

    public String deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
        return "Comment Deleted";
    }

    public List<CommentResponseDto> showComments(){
        List<Comment> comments = commentRepository.findAll();
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment:comments){
           CommentResponseDto commentResponseDto = getComment(comment);
           commentResponseDtos.add(commentResponseDto);
        }
        return commentResponseDtos;
    }

    public CommentResponseDto showComment(Long commentId){
        Comment comment = commentRepository.findById(commentId).get();
        return getComment(comment);
    }




}
