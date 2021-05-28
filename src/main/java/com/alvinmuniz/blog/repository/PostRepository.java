package com.alvinmuniz.blog.repository;

import com.alvinmuniz.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {


}
