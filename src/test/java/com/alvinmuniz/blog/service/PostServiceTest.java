package com.alvinmuniz.blog.service;

import com.alvinmuniz.blog.model.Post;
import com.alvinmuniz.blog.model.User;
import com.alvinmuniz.blog.repository.PostRepository;
import com.alvinmuniz.blog.repository.UserRepository;
import com.alvinmuniz.blog.security.WithCustomUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    PostRepository postRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    SecurityContextHolder securityContextHolder;

    @InjectMocks
    PostService postService;

    @Test
    public void it_should_return_all_posts() {
        Post post1 = Post.builder()
                .title("Hello")
                .date(null)
                .build();

        Post post2 = Post.builder()
                .title("There")
                .date(null)
                .build();

        List<Post> expected = Arrays.asList(post1, post2);

        when(postRepository.findAll()).thenReturn(Arrays.asList(post1, post2));

        List<Post> actual = postService.getAllPosts();

        for (int i = 0; i < expected.size(); i++)
            assertThat(expected.get(i)).isEqualTo(actual.get(i));

    }




}
