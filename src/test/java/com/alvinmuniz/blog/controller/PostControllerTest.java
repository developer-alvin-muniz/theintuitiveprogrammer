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

import java.util.Arrays;
import java.util.List;

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

        //GIVEN
        //WHEN
        //THEN
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

    @Test
    public void it_should_return_list_icecream() throws Exception {

        //GIVEN
        //WHEN
        //THEN
        List<String> expected  = Arrays.asList(
                "Strawberry", "Chocolate", "Purple"
        );
        when(postService.listOfIceCreamFlavors(any())).thenReturn(expected);

        List<String> actual = postController.getAllFlavors("Blue");

        verify(postService).listOfIceCreamFlavors("Blue");

        verifyNoMoreInteractions(postService);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void it_should_retrieve_posts_by_user_id() {
        List<Post> expectedList = Arrays.asList(
                Post.builder()
                        .title("First")
                        .date(null)
                        .userId(3L)
                        .build(),
                Post.builder()
                        .title("Second")
                        .userId(3L)
                        .date(null)
                        .build()
        );

        when(postService.findPostsByUserId(3L)).thenReturn(expectedList);

        List<Post> actualList = Arrays.asList(
                Post.builder()
                        .title("First")
                        .date(null)
                        .userId(3L)
                        .build(),
                Post.builder()
                        .title("Second")
                        .date(null)
                        .userId(3L)
                        .build()
        );

       List<Post> actualPostList = postController.findPostsByUserId(1L);

       verify(postService).findPostsByUserId(1L);
       verifyNoMoreInteractions(postService);
    }
}
