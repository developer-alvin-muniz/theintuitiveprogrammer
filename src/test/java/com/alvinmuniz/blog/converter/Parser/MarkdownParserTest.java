package com.alvinmuniz.blog.converter.Parser;

import com.alvinmuniz.blog.converter.TagType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MarkdownParserTest {

    @InjectMocks
    MarkdownParser markdownParser;

    @BeforeEach
    void setup() {
        initMocks(this);
    }

    @Test
    @DisplayName("Take in a list and map it to html tags")
    void mapTextToMarkdownTap() {

        List<String> given = Arrays.asList(
                "# Hello",
                "This is the body of text following the header",
                "Here is another line of text"
        );


        Map<TagType, List<String>> result =
                markdownParser.mapTextToMarkdownTap(given);

        assertTrue(result.containsKey(TagType.H1));

        assertEquals(result.size(), 3);
    }

    @Test
    void removeHeadingMarkdownSymbol()  {
        List<String> given = Arrays.asList(
                "# Hello",
                "This is the body of text following the header",
                "Here is another line of text"
        );

        Map<TagType, List<String>> result =
                markdownParser.mapTextToMarkdownTap(given);

        assertEquals(result.get(TagType.H1).get(0), ("Hello"));
        assertEquals(result.get(TagType.H1).get(1), ("This is the body of text following the header"));
        assertEquals(result.get(TagType.H1).get(2),"Here is another line of text");
    }

}
