package com.alvinmuniz.blog.controller;

import com.alvinmuniz.blog.model.Post;
import com.alvinmuniz.blog.model.User;
import com.alvinmuniz.blog.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
class PostControllerTest {

    private Post expectedPost;

    @Mock
    PostService postService;

    @InjectMocks
    PostController postController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        expectedPost = Post.builder()
                .title("Expected")
                .date(null)
                .build();
    }


    @AfterEach
    void tearDown() {
    }


    @Test
    public void it_should_return_created_post() throws Exception {
        when(postService.createPost(any())).thenReturn(expectedPost);

        Post requestPost = Post.builder()
                .title("Expected")
                .date(null)
                .build();
        Post addedPost = postController.createPost(requestPost);

        verify(postService).createPost(requestPost);
        verifyNoMoreInteractions(postService);
        assertThat(addedPost).isEqualToComparingFieldByField(expectedPost);
    }
}
