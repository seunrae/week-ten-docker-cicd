package com.example.blog.controller;

import com.example.blog.dtos.userdtos.UserLoginDto;
import com.example.blog.dtos.userdtos.UserRegisterDto;
import com.example.blog.dtos.userdtos.UserResponseDto;
import com.example.blog.models.User;
import com.example.blog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show-users")
    public ResponseEntity<List<UserResponseDto>> showAllUsers(){
        List<UserResponseDto> users = userService.showUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/show-user/{userId}")
    public ResponseEntity<UserResponseDto> showUserById(@PathVariable Long userId){
        UserResponseDto userResponseDto  = userService.showUser(userId);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/register-user")
    public ResponseEntity<String> createUser(@RequestBody UserRegisterDto userRegisterDto){
        String regUser = userService.registerUser(userRegisterDto);
        return new ResponseEntity<>(regUser,HttpStatus.CREATED);
    }

    @PostMapping("/login-user")
    private ResponseEntity<String> verifyUser(@RequestBody UserLoginDto userLoginDto){
        String authUser = userService.loginUser(userLoginDto);
        return  new ResponseEntity<>(authUser, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/update-user/{userId}")
    private ResponseEntity<String> updateUser(@RequestBody UserRegisterDto userRegisterDto ,@PathVariable Long userId){
        String updUser = userService.updateUser(userRegisterDto, userId);
        return  new ResponseEntity<>(updUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete-user/{userId}")
    private ResponseEntity<String> deleteUser(@PathVariable Long userId){
       String delUser = userService.deleteUser(userId);
        return new ResponseEntity<>(delUser, HttpStatus.NO_CONTENT);
    }


}
