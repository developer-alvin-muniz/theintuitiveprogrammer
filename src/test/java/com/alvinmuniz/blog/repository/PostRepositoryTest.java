package com.alvinmuniz.blog.repository;

import com.alvinmuniz.blog.model.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PostRepository postRepository;

    @Test
    public void is_should_save_post() {
        Post testPost = new Post();
        testPost.setTitle("Test Post");
        Post foundPost = entityManager.persistAndFlush(testPost);
        assertThat(postRepository.findById(testPost.getId()).get()).isEqualTo(testPost);
    }
}
