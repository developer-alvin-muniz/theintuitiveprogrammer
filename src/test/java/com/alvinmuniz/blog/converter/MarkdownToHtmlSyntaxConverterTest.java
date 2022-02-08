package com.alvinmuniz.blog.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MarkdownToHtmlSyntaxConverterTest {

    @InjectMocks
    MarkdownToHtmlSyntaxConverter markdownToHtmlSyntaxConverter;

    @BeforeEach
    void setUp(){ initMocks(this);}

    @Test
    void headerToContentMapTest() {

        List<String> givenLines = Arrays.asList(
                "### Line 1",
                "Line 2",
                "Line 3"
        );

        Map<TagType, List<String>> given = new HashMap<>();

        given.put(TagType.H1, givenLines);

        String result = markdownToHtmlSyntaxConverter.headerToContentMap(given);

        assertThat(result).isEqualTo("<H1>### Line 1</H1>"+
                "<P>"+"Line 2"+ "Line 3"+ "</P>");
    }

}
