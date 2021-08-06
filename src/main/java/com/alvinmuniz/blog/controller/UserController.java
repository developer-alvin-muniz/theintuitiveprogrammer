package com.alvinmuniz.blog.controller;

import com.alvinmuniz.blog.model.Login.LoginRequest;
import com.alvinmuniz.blog.model.Login.LoginResponse;
import com.alvinmuniz.blog.model.User;
import com.alvinmuniz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/users")
public class UserController {

    private UserService userService;

    private final String BASE_URL = "/auth/users";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<User> userRegister(@RequestBody User user) {
        return new ResponseEntity<>(this.userService.saveUser(user), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> loginRegister(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.toString() + " controller hit");
        return this.userService.loginUser(loginRequest);
    }

}
