package com.alvinmuniz.blog.service;

import com.alvinmuniz.blog.model.Login.LoginRequest;
import com.alvinmuniz.blog.model.Login.LoginResponse;
import com.alvinmuniz.blog.model.User;
import com.alvinmuniz.blog.repository.UserRepository;
import com.alvinmuniz.blog.security.JWTUtils;
import com.alvinmuniz.blog.security.MyUserDetails;
import com.alvinmuniz.blog.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    /*Authentication manager for the security context*/
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;


    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, MyUserDetails myUserDetails) {
        this.userRepository = userRepository;
    }



    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        System.out.println(loginRequest.toString());

        Long userId =
                userRepository.findByUsername(loginRequest.getUsername()).getId();

        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        final UserDetails userDetails =
                userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String JWT = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(JWT,
                userId));

    }

    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
