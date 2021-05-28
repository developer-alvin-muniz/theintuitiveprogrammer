package com.alvinmuniz.blog.service;

import com.alvinmuniz.blog.model.Post;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    public Post save(Post post) {
        return new Post();
    }

    public Post createPost(Post testPost) {

        return new Post();
    }
}
