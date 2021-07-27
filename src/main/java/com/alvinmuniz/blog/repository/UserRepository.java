package com.alvinmuniz.blog.repository;

import com.alvinmuniz.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);


}
