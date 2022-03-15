package com.alvinmuniz.blog.service;

import com.alvinmuniz.blog.model.Post;
import com.alvinmuniz.blog.model.User;
import com.alvinmuniz.blog.repository.PostRepository;
import com.alvinmuniz.blog.repository.UserRepository;
import com.alvinmuniz.blog.security.WithCustomUser;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
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

//    @Test
//    @DisplayName("should return a list of names")
//    public void shouldReturnAListOfNames() {
//
//        List<String> expected = Arrays.asList(
//                "John", "Jerri", "Joseph"
//        );
//
//        List<String> actualList = postService.listOfNames();
//
//        assertEquals(expected,actualList);
//
//    }


    @Test
    @DisplayName("should return a list of names")
    public void shouldReturnListOfIceCream() {
        //GIVEN
        String flavor = "Blue";

        List<String> expected  = Arrays.asList(
                "Strawberry", "Chocolate", "Purple"
        );

        //WHEN
        List<String> actual = postService.listOfIceCreamFlavors(flavor);

        //THEN
        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("should return a list of names")
    public void shouldReturnListOfIceCream_Empty() {
        //GIVEN
        String flavor = "Red";

        List<String> expected  = Collections.emptyList();

        //WHEN
        List<String> actual = postService.listOfIceCreamFlavors(flavor);

        //THEN
        assertEquals(actual, expected);
    }
}
