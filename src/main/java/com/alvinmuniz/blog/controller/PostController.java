package com.alvinmuniz.blog.controller;


import com.alvinmuniz.blog.model.Post;
import com.alvinmuniz.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @GetMapping("/")
    public void indexPage() {
//        return getAllPosts();
    }

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("posts")
    public Post createPost(@RequestBody Post post) {
        System.out.println(post);
        return this.postService.createPost(post);
    }

}
