package com.alvinmuniz.blog.controller;


import com.alvinmuniz.blog.model.Post;
import com.alvinmuniz.blog.security.MyUserDetails;
import com.alvinmuniz.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private PostService postService;

    private MyUserDetails myUserDetails;

    @Autowired
    public PostController(PostService postService, MyUserDetails myUserDetails) {
        this.postService = postService;
        this.myUserDetails = myUserDetails;
    }

    @GetMapping("posts")
    public List<Post> getAllPosts() {
    System.out.println(" All posts being retrieved");
        return this.postService.getAllPosts();
    }

    @GetMapping("admin/posts/{userId}")
    public List<Post> findPostsByUserId(@PathVariable Long userId) {
        return this.postService.findPostsByUserId(userId);
    }

    @PostMapping("admin/posts")
    public Post createPost(@RequestBody Post post) {
        System.out.println("Post controller hit wiht " + post.toString());
        Post savedPost = this.postService.createPost(post);

        System.out.println("Saved Post" + savedPost.toString());
        return savedPost;
    }
}
