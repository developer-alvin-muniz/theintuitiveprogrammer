package com.alvinmuniz.blog;

import com.alvinmuniz.blog.repository.UserRepository;
import com.alvinmuniz.blog.service.MarkdownConverterService;
import com.alvinmuniz.blog.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class BlogApplication {


    private final MarkdownConverterService markdownConverterService;

    public BlogApplication(MarkdownConverterService markdownConverterService) {
        this.markdownConverterService = markdownConverterService;
    }

    public static void main(String[] args) throws IOException {

        SpringApplication.run(BlogApplication.class, args);
    }

    @PostConstruct
    void setUp() throws IOException {
        this.markdownConverterService.convertAllBlogPostToHtml();
    }

}
