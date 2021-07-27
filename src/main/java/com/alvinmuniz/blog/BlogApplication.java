package com.alvinmuniz.blog;

import com.alvinmuniz.blog.model.User;
import com.alvinmuniz.blog.repository.UserRepository;
import com.alvinmuniz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class BlogApplication {


    UserRepository userRepository;
    UserService userService;

    public BlogApplication(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BlogApplication.class, args);
    }

    @PostConstruct
    void setUp() {
        User user = new User("alvin","12345");
        this.userService.saveUser(user);
    }

}
