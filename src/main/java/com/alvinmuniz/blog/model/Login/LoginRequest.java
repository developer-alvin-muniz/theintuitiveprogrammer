package com.alvinmuniz.blog.model.Login;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoginRequest {

    private String username;
    private String password;

}
