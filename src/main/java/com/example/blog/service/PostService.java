package com.example.blog.service;

import com.example.blog.dtos.commentdtos.CommentPostReposnseDto;
import com.example.blog.dtos.commentdtos.CommentResponseDto;
import com.example.blog.dtos.customerdtos.CustomerResponseDto;
import com.example.blog.dtos.postdtos.PostCommentResponseDto;
import com.example.blog.dtos.postdtos.PostCreateDto;
import com.example.blog.dtos.postdtos.PostResponseDto;
import com.example.blog.dtos.userdtos.UserResponseDto;
import com.example.blog.models.*;
import com.example.blog.repositories.CommentRepository;
import com.example.blog.repositories.LikeRepository;
import com.example.blog.repositories.PostRepository;
import com.example.blog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    public PostResponseDto createPost(PostCreateDto postCreateDto,Long userId){
        User user = userRepository.findById(userId).get();
        Post post  = new Post();
        post.setDesignTitle(postCreateDto.getDesignTitle());
        post.setDesignDescription(postCreateDto.getDesignDescription());
        post.setUser(user);
        postRepository.save(post);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setUserEmail(user.getUserEmail());
        userResponseDto.setUserContact(user.getUserContact());

        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setDesignTitle(post.getDesignTitle());
        postResponseDto.setDesignDescription(post.getDesignDescription());
        postResponseDto.setCreatedAt(post.getCreatedAt());
        postResponseDto.setUser(userResponseDto);

        return postResponseDto;
    }

    public List<PostResponseDto> showPosts(){
        List<Post> posts = postRepository.findAll();
        List<Like> likes = likeRepository.findAll();
        List<Like> newLikes = new ArrayList<>();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for(Post post:posts){
            PostResponseDto postResponseDto = new PostResponseDto();
            postResponseDto.setDesignId(post.getDesignId());
            postResponseDto.setDesignTitle(post.getDesignTitle());
            postResponseDto.setDesignDescription(post.getDesignDescription());
            postResponseDto.setCreatedAt(post.getCreatedAt());
            postResponseDto.setUser(getUser(post.getUser()));
            postResponseDto.setComments(getComments(post.getCustomerComments()));
            postResponseDto.setLikes(post.getLikes().size());
            postResponseDtos.add(postResponseDto);
        }
        return postResponseDtos;
    }

    public PostCommentResponseDto Post(Post post){
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
        commentResponseDto.setPost(Post(comment.getPost()));

        return commentResponseDto;
    }
    public List<CommentPostReposnseDto> getComments(List<Comment> comments) {
        List<CommentPostReposnseDto> commentPostReposnseDtos = new ArrayList<>();
        for (Comment comment:comments){
            CommentPostReposnseDto commentPostReposnseDto = new CommentPostReposnseDto();
            commentPostReposnseDto.setCommentText(comment.getCommentText());
            commentPostReposnseDto.setCustomer(getCustomer(comment.getCommentAuthor()));
            commentPostReposnseDtos.add(commentPostReposnseDto);
        }
        return commentPostReposnseDtos;
    }

    public List<CommentResponseDto> comments(List<Comment> comments){
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment:comments){
            CommentResponseDto commentResponseDto = getComment(comment);
            commentResponseDtos.add(commentResponseDto);
        }
        return commentResponseDtos;
    }

    public CustomerResponseDto getCustomer(Customer customer){
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setCustomerUserName(customer.getCustomerUserName());
        customerResponseDto.setCustomerEmail(customer.getCustomerEmail());
        customerResponseDto.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());

        return customerResponseDto;
    }

    private UserResponseDto getUser(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setUserEmail(user.getUserEmail());
        userResponseDto.setUserContact(user.getUserContact());
        return userResponseDto;
    }

    private PostResponseDto getPost(Post post){
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setDesignId(post.getDesignId());
        postResponseDto.setDesignTitle(post.getDesignTitle());
        postResponseDto.setDesignDescription(post.getDesignDescription());
        postResponseDto.setCreatedAt(post.getCreatedAt());
        postResponseDto.setUser(getUser(post.getUser()));
        postResponseDto.setComments(getComments(post.getCustomerComments()));
        postResponseDto.setLikes(post.getLikes().size());
        return postResponseDto;
    }

    private List<PostResponseDto> getPosts (List<Post> posts){
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for(Post post:posts){
            PostResponseDto postResponseDto = new PostResponseDto();
            postResponseDto.setDesignId(post.getDesignId());
            postResponseDto.setDesignTitle(post.getDesignTitle());
            postResponseDto.setDesignDescription(post.getDesignDescription());
            postResponseDto.setCreatedAt(post.getCreatedAt());
            postResponseDto.setUser(getUser(post.getUser()));
            postResponseDto.setComments(getComments(post.getCustomerComments()));
            postResponseDto.setLikes(post.getLikes().size());
            postResponseDtos.add(postResponseDto);
        }
        return postResponseDtos;
    }


    public PostResponseDto showPost(Long postId){
        Post post = postRepository.findById(postId).get();
        return getPost(post);
    }

    public PostResponseDto updatePost(PostCreateDto postCreateDto,Long postId){
        Post post = postRepository.findById(postId).get();
        post.setDesignTitle(postCreateDto.getDesignTitle());
        post.setDesignDescription(postCreateDto.getDesignDescription());
        postRepository.save(post);
        return getPost(post);
    }

    public  String deletePost(Long postId){
        postRepository.deleteById(postId);
        return "Post deleted!!";
    }


    public List<PostResponseDto> showPostTitle(String title){
        List<Post> posts = postRepository.findAllByDesignTitle(title);
        return getPosts(posts);
    }

    public List<CommentResponseDto> showPostComment(String comment){
        List<Comment> posts = commentRepository.findAllByCommentText(comment);

        return comments(posts);
    }

}
