package com.alvinmuniz.blog.integration;

import com.alvinmuniz.blog.model.Post;
import com.alvinmuniz.blog.model.User;
import com.alvinmuniz.blog.repository.PostRepository;
import com.alvinmuniz.blog.repository.UserRepository;
import com.alvinmuniz.blog.security.WithCustomUser;
import com.alvinmuniz.blog.service.PostService;
import com.alvinmuniz.blog.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostIntegrationTest {

    private final WebApplicationContext webApplicationContext;

    @Autowired
    private final PostRepository postRepository;

     private final UserRepository userRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    Post expectedPost;


    @Autowired
    public PostIntegrationTest(WebApplicationContext webApplicationContext,
                               PostRepository postRepository, UserRepository userRepository, UserService userService, PostService postService) {
        this.webApplicationContext = webApplicationContext;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setUp() {
        initMocks(this);
        mockMvc =
                MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        objectMapper = new ObjectMapper();

        expectedPost = Post.builder()
                .title("Expected")
                .date(null)
                .build();
    }

    @Test
    @WithCustomUser(username = "Alvin")
    @DisplayName("It should save a post successfully with a userId at admin/post")
     public void save_post_with_userId() throws Exception {
        //GIVEN

        Post expectedPostEntity = Post.builder()
                .title("Expected")
                .content("Hello")
                .date(null)
                .build();

        //WHEN
        expectedPostEntity = postRepository.save(expectedPostEntity);

        String jsonPost = objectMapper.writeValueAsString(expectedPostEntity);

        //THEN
        MvcResult mvcResult =
                        mockMvc.perform(post("/admin/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPost))
                                .andExpect(status().isOk())
                                .andReturn();
        Post actualPost =
                objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Post.class);

        assertThat(expectedPostEntity).isEqualToIgnoringGivenFields( actualPost,"userId","date");

    }

    @Test
    @DisplayName("It should return a list of all of the posts stored")
    public void getAllPostsTest() throws Exception {
        //GIVEN

        List<Post> expectedList = postRepository.findAll();


        //WHEN
        expectedList.forEach(post -> postRepository.save(post));

        String jsonList = objectMapper.writeValueAsString(expectedList);

        //THEN
        MvcResult mvcResult =
                mockMvc.perform(get("/admin/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        assertThat(jsonList).isEqualTo(mvcResult.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("It should return a list of all of the posts saved under a userId")
    public void getAllPostsByUserId() throws Exception {
        userRepository.save(User.builder()
                .id(2L)
                .username("Test 2")
                .password("12345")
                .build());

        // Given a list of posts
        List<Post> expectedList = Arrays.asList(
                Post.builder()
                        .userId(2L)
                        .title("First")
                        .date(null)
                        .build(),
                Post.builder()
                        .userId(2L)
                        .title("Second")
                        .date(null)
                        .build()
        );

        // When I hit the end point with a user id such as admin/posts/:number

        expectedList.forEach(post -> postRepository.save(post));


        String jsonPostList = objectMapper.writeValueAsString(expectedList);

        MvcResult mvcResult =
                mockMvc.perform(get("/admin/posts/2")
                        .content(jsonPostList)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        assertThat(jsonPostList).isEqualTo(mvcResult.getResponse().getContentAsString());
    }



}
