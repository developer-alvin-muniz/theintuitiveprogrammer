package com.alvinmuniz.blog.model.Login;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoginResponse {

    private String jwt;
    private Long id;

}
