package com.example.blog.service;

import com.example.blog.dtos.postdtos.PostResponseDto;
import com.example.blog.dtos.userdtos.UserLoginDto;
import com.example.blog.dtos.userdtos.UserRegisterDto;
import com.example.blog.dtos.userdtos.UserResponseDto;
import com.example.blog.models.Post;
import com.example.blog.models.User;
import com.example.blog.repositories.PostRepository;
import com.example.blog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public String registerUser(UserRegisterDto userRegisterDto){
        User user  = new User();
        user.setUserName(userRegisterDto.getUserName());
        user.setUserEmail(userRegisterDto.getUserEmail());
        user.setUserPassword(userRegisterDto.getUserPassword());
        user.setUserContact(userRegisterDto.getUserContact());
        user.setCreatedAt(new Date());
        userRepository.save(user);

        return "Thank you! "+user.getUserName()+" your registration has been successful!!";
    }

    public String loginUser(UserLoginDto userLoginDto){
        User user = userRepository.findByUserEmailAndUserPassword(userLoginDto.getUserEmail(), userLoginDto.getUserPassword());
        if(Objects.nonNull(user)){
            return "Login successful!\nWelcome "+user.getUserName();
        }
        else if (userLoginDto.getUserEmail().equals("") && userLoginDto.getUserPassword().equals("")){
            return "Enter your user name or password";
        }
        return "Enter correct credentials";
    }

    public String updateUser(UserRegisterDto userRegisterDto, Long userId){
        User user  = userRepository.findById(userId).get();
        user.setUserName(userRegisterDto.getUserName());
        user.setUserEmail(userRegisterDto.getUserEmail());
        user.setUserPassword(userRegisterDto.getUserPassword());
        user.setUserContact(userRegisterDto.getUserContact());
        user.setCreatedAt(new Date());
        userRepository.save(user);

        return "Credentials Updated";
    }
    public String deleteUser(Long userId){
        userRepository.deleteById(userId);
        return "Account deleted!";
    }

    public List<UserResponseDto> showUsers(){
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(User user: users){
//            List<Post> posts = postRepository.findAll();
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setUserId(user.getUserId());
            userResponseDto.setUserName(user.getUserName());
            userResponseDto.setUserEmail(user.getUserEmail());
            userResponseDto.setUserContact(user.getUserContact());
            userResponseDto.setDesigns(getPosts(user.getUserDesigns()));
            userResponseDtos.add(userResponseDto);
        }
        return userResponseDtos;
    }

    private UserResponseDto getUser(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setUserEmail(user.getUserEmail());
        userResponseDto.setUserContact(user.getUserContact());
        userResponseDto.setDesigns(getPosts(user.getUserDesigns()));
        return userResponseDto;
    }

    private PostResponseDto getPost(Post post){
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setDesignTitle(post.getDesignTitle());
        postResponseDto.setDesignDescription(post.getDesignDescription());
        postResponseDto.setLikes(post.getLikes().size());
        postResponseDto.setCreatedAt(post.getCreatedAt());

        return postResponseDto;
    }
    private List<PostResponseDto> getPosts(List<Post> posts){
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for(Post post: posts) {
            PostResponseDto postResponseDto = new PostResponseDto();
            postResponseDto.setDesignId(post.getDesignId());
            postResponseDto.setDesignTitle(post.getDesignTitle());
            postResponseDto.setDesignDescription(post.getDesignDescription());
            postResponseDto.setCreatedAt(post.getCreatedAt());
            postResponseDto.setLikes(post.getLikes().size());
            postResponseDtos.add(postResponseDto);
        }
        return postResponseDtos;
    }



    public UserResponseDto showUser(Long userId){
        User user = userRepository.findById(userId).get();
        return getUser(user);
    }
}
