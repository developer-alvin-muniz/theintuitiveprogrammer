package com.alvinmuniz.blog.service;

import com.alvinmuniz.blog.model.Post;
import com.alvinmuniz.blog.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postService;

    @Test
    public void when_save_post_it_should_return_post() {
        Post testPost = new Post();
        testPost.setTitle("Test Post");
        testPost.setDate(new Date());
        when(postRepository.save(any(Post.class))).thenReturn(testPost);
        Post createdPost = postService.createPost(testPost);
        assertThat(createdPost.getTitle()).isSameAs(testPost.getTitle());
    }
}
