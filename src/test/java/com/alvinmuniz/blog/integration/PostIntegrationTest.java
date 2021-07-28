package com.alvinmuniz.blog.integration;

import com.alvinmuniz.blog.model.Post;
import com.alvinmuniz.blog.repository.PostRepository;
import com.alvinmuniz.blog.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Autowired
    private final UserRepository userRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    Post expectedPost;


    @Autowired
    public PostIntegrationTest(WebApplicationContext webApplicationContext, PostRepository postRepository, UserRepository userRepository) {
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
    @Transactional
    public void getByIdTest() throws Exception {
        //GIVEN

        Post expectedPostEntity = Post.builder()
                .id(1L)
                .title("Expected")
                .date(null)
                .build();

        //WHEN
        expectedPost = postRepository.save(expectedPost);
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

        assertThat(expectedPost).isEqualTo(actualPost);
    }

    @Test
    public void getAllPostsTest() throws Exception {
        //GIVEN

        Post expectedPostEntity = Post.builder()
                .title("Expected")
                .date(null)
                .build();

        Post expectedPostEntity2 = Post.builder()
                .title("Expected2")
                .date(null)
                .build();


        //WHEN
        List<Post> expectedList = Arrays.asList(expectedPostEntity,
                expectedPostEntity2);
        expectedList.forEach(post -> postRepository.save(post));

        String jsonList = objectMapper.writeValueAsString(expectedList);

        //THEN
        MvcResult mvcResult =
                mockMvc.perform(get("/admin/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        System.out.println(jsonList);
        System.out.println(mvcResult.getResponse().getContentAsString());

        assertThat(jsonList).isEqualTo(mvcResult.getResponse().getContentAsString());
    }



}
