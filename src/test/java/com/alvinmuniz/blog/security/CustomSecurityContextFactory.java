package com.alvinmuniz.blog.security;


import com.alvinmuniz.blog.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

/*** Allows us to access the end points needing authentication with a custom
user details and annotation***/

public class CustomSecurityContextFactory
        implements WithSecurityContextFactory<WithCustomUser> {

    @Override
    public SecurityContext createSecurityContext(
            WithCustomUser withCustomUser) {
        SecurityContext context =
                SecurityContextHolder.createEmptyContext();

        User user = new User(100L,withCustomUser.username(),"123456");

        MyUserDetails myUserDetails = new MyUserDetails(user);

        var a = new UsernamePasswordAuthenticationToken(
                myUserDetails, null, null);

        context.setAuthentication(a);

        return context;
    }
}
