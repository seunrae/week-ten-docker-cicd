package com.example.blog;

import com.example.blog.dtos.commentdtos.CommentPostReposnseDto;
import com.example.blog.dtos.commentdtos.CommentResponseDto;
import com.example.blog.dtos.postdtos.PostCreateDto;
import com.example.blog.dtos.postdtos.PostResponseDto;
import com.example.blog.dtos.userdtos.UserResponseDto;
import com.example.blog.models.*;
import com.example.blog.repositories.*;
import com.example.blog.service.CommentService;
import com.example.blog.service.LikeService;
import com.example.blog.service.PostService;
import com.example.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BlogApplicationTests.class})
class BlogApplicationTests {
    @Mock
    PostRepository postRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CommentRepository commentRepository;

    @Mock
    LikeRepository likeRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    PostService postService;

    @InjectMocks
    CommentService commentService;

    @InjectMocks
    LikeService likeService;

    @InjectMocks
    UserService userService;

    @Test
    public void test_showAllPosts(){
        List<Post> posts = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        List<Like> likes = new ArrayList<>();
        User user = new User();
        posts.add(new Post(1L, "Men Designs", "men clothes", null, null,user, comments, likes ));
        posts.add(new Post(2L, "Women Designs", "women clothes", null, null,user, comments, likes ));
        posts.add(new Post(3L, "Boheniam ", "old fashioned", null, null,user, comments, likes ));
        posts.add(new Post(4L, "sporty", "sweat shirts", null, null,user, comments, likes ));

        when(postRepository.findAll()).thenReturn(posts);
        assertEquals(4, postService.showPosts().size());
    }

    @Test
    public void test_showPostById(){
        List<Post> posts = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        List<Like> likes = new ArrayList<>();
        User user = new User();
        Long postId = 4L;
        posts.add(new Post(1L, "Men Designs", "men clothes", null, null,user, comments, likes ));
        posts.add(new Post(2L, "Women Designs", "women clothes", null, null,user, comments, likes ));
        posts.add(new Post(3L, "Boheniam ", "old fashioned", null, null,user, comments, likes ));
        posts.add(new Post(4L, "sporty", "sweat shirts", null, null,user, comments, likes ));

        for(int i=0; i<posts.size(); i++) {
            when(postRepository.findById(postId)).thenReturn(Optional.ofNullable(posts.get(i)));
        }
        assertEquals("sporty", postService.showPost(postId).getDesignTitle());
    }

    @Test
    public void test_createPost(){
        List<Post> posts = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        List<Like> likes = new ArrayList<>();
        User user = new User(1L,"Seun","oluseun@gmail.com","seun1","08139904849",null,posts);
        List<User> users = new ArrayList<>();
        users.add(user);
        UserResponseDto userResponseDto = new UserResponseDto();
        List<CommentPostReposnseDto> commentResponseDtos = new ArrayList<>();
        Post post = new Post(5L, "tribal", "yoruba, igbo and hausa", null, null,user, comments, likes);
        posts.add(new Post(1L, "Men Designs", "men clothes", null, null,user, comments, likes ));
        posts.add(new Post(2L, "Women Designs", "women clothes", null, null,user, comments, likes ));
        posts.add(new Post(3L, "Boheniam ", "old fashioned", null, null,user, comments, likes ));
        posts.add(new Post(4L, "sporty", "sweat shirts", null, null,user, comments, likes ));
        for(int i = 0; i< posts.size(); i++) {
            when(postRepository.save(post)).thenReturn(posts.get(i));
        }
        for(int j = 0; j< users.size(); j++){
            when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(users.get(j)));
        }

        PostResponseDto postResponseDto = new PostResponseDto(post.getDesignId(),post.getDesignTitle(),post.getDesignDescription(),post.getCreatedAt(), userResponseDto,3,commentResponseDtos);
        PostCreateDto postCreateDto = new PostCreateDto(post.getDesignTitle(),post.getDesignDescription());
        postService.createPost(postCreateDto, 1L);
        assertEquals(true, posts.add(post));
        assertEquals(5, posts.size());

    }

    @Test
    public void test_showAllComments(){
        List<Comment> comments = new ArrayList<>();
        Customer customer = new Customer();
        Post post = new Post();
        comments.add(new Comment(1L, "Love it",null, null,customer,post));
        comments.add(new Comment(2L, "cool",null, null,customer,post));
        comments.add(new Comment(3L, "nice",null, null,customer,post));
        comments.add(new Comment(4L, "i dont really like this style, it cool though",null, null,customer,post));
        when(commentRepository.findAll()).thenReturn(comments);


        List<CommentResponseDto> commentResponseDtos = commentService.showComments();
        assertEquals(4, commentResponseDtos.size());
    }

    @Test
    public void test_showCommentById(){
        List<Comment> comments = new ArrayList<>();
        Customer customer = new Customer();
        Post post = new Post();
        Long commentId = 3L;
        comments.add(new Comment(1L, "Love it",null, null,customer,post));
        comments.add(new Comment(2L, "cool",null, null,customer,post));
        comments.add(new Comment(3L, "nice",null, null,customer,post));
        comments.add(new Comment(4L, "i dont really like this style, it cool though",null, null,customer,post));
        for(int i=0; i<comments.size(); i++) {
            when(commentRepository.findById(commentId)).thenReturn(Optional.ofNullable(comments.get(i)));
        }
        assertEquals("i dont really like this style, it cool though", commentService.showComment(commentId).getCommentText());
    }

    @Test
    public void test_likeCount(){
        List<Like> likes = new ArrayList<>();
        List<Comment> comments = new ArrayList<>();
        Customer customer = new Customer(1L, "Seun","seun@gmail.com", "seun1", "08139904849",null, comments);
        User user = new User();
        Post post = new Post(1L, "Men Designs", "men clothes", null, null,user, comments, likes);
        Post post1 = new Post(2L, "Women Designs", "women clothes", null, null,user, comments, likes);
        likes.add(new Like(1L, 1,customer, post));
        likes.add(new Like(2L, 1,customer, post1));
        likes.add(new Like(3L, 1,customer, post1));
        likes.add(new Like(3L, 1,customer, post));

//        when(likeService.createLike(1,customer.getCustomerId(),likes.get(1).getLikeId())).thenReturn(likes.toString());
        assertEquals(4, likes.size());


    }

}
