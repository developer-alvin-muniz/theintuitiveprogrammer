package com.alvinmuniz.blog;

import com.alvinmuniz.blog.model.User;
import com.alvinmuniz.blog.service.MarkdownConverterService;
import com.alvinmuniz.blog.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

@SpringBootApplication
public class BlogApplication {


    private final MarkdownConverterService markdownConverterService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public BlogApplication(MarkdownConverterService markdownConverterService, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.markdownConverterService = markdownConverterService;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public static void main(String[] args) throws IOException {

        SpringApplication.run(BlogApplication.class, args);
    }

    @PostConstruct
    void setUp() throws IOException {
        this.markdownConverterService.convertAllBlogPostToHtml();

        Optional<User> user = Optional.ofNullable(userService.findByUsername("test"));
        if(user.isEmpty()) {
            User testUser  = userService.saveUser(
                    User.builder()
                            .username("test")
                            .password("test")
                            .build());
        }





    }

}
