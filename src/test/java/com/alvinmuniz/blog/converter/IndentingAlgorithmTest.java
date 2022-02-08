package com.alvinmuniz.blog.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

class IndentingAlgorithmTest {

    @InjectMocks
    IndentingAlgorithm indentingAlgorithm;

    @BeforeEach
    void setup() {
        initMocks(this);
    }

    @Test
    @DisplayName("Opening Tag like <html>")
    void detectTag_True() {

        boolean actual = indentingAlgorithm.isTag("<html>");
        assertTrue(actual);
    }

    @Test
    @DisplayName("Opening Tag like <html will return false")
    void detectTag_False() {
        boolean actual = indentingAlgorithm.isTag("<html");
        assertFalse(actual);
    }

    @Test
    @DisplayName("Opening Tag like </html> will return TRUE")
    void detectClosingTag_True() {
        assertTrue(indentingAlgorithm.isClosingTag("</html>"));
    }

    @Test
    @DisplayName("Opening Tag like <html will return false")
    void detectClosingTag_False() {
        boolean actual = indentingAlgorithm.isClosingTag("<html");
        assertFalse(actual);
    }

    @Test
    @DisplayName("detects the current level of indentation")
    void detectIndentationLevel() {

        String given = "<html><h1></h1></html>";



    }



}
