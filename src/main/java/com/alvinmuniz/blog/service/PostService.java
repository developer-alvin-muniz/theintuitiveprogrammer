package com.alvinmuniz.blog.service;

import com.alvinmuniz.blog.model.Post;
import com.alvinmuniz.blog.model.User;
import com.alvinmuniz.blog.repository.PostRepository;
import com.alvinmuniz.blog.repository.UserRepository;
import com.alvinmuniz.blog.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private MyUserDetails myUserDetails;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, MyUserDetails myUserDetails) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.myUserDetails = myUserDetails;
    }

    public Post createPost(Post post) {
        MyUserDetails userDetails = (MyUserDetails)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setUserId(userDetails.getUser().getId());
        Post savedPost = this.postRepository.save(post);
        return this.postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return this.postRepository.findAll();
    }

    public List<Post> findPostsByUserId(Long userId) {
        Optional<User> foundUser = this.userRepository.findById(userId);

        return foundUser.isPresent() ?
                this.postRepository.findAllByUserId(userId)
                : Arrays.asList();
    }

    public List<String> listOfNames() {
        return Arrays.asList(
                "John", "Jerry", "Joseph"
        );
    }
}
