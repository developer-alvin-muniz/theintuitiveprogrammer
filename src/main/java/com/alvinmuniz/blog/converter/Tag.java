package com.alvinmuniz.blog.converter;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Tag {

    TagType type;
    String symbol;

}
