package com.alvinmuniz.blog.service;

import com.alvinmuniz.blog.converter.FileService;
import com.alvinmuniz.blog.converter.MarkdownConverter;
import com.alvinmuniz.blog.converter.MarkdownToHtmlSyntaxConverter;
import com.alvinmuniz.blog.converter.Parser.FileParser;
import com.alvinmuniz.blog.converter.Parser.MarkdownParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MarkdownConverterServiceTest {

    @InjectMocks
    MarkdownConverterService markdownConverterService;

    @Mock
    MarkdownConverter markdownConverter;

    @Mock
    FileParser fileParser;

    @Mock
    MarkdownParser markdownParser;

    @Mock
    FileService fileService;

    @Mock
    MarkdownToHtmlSyntaxConverter markdownToHtmlSyntaxConverter;

    @BeforeEach
    void setup() {initMocks(this);}

    @Test
    void conversionServiceTest() throws IOException {

        //given
        File markdownDirectory = new File("directory");
        if (!markdownDirectory.isDirectory()) markdownDirectory.mkdir();
        File markdownFile = new File("directory", "markdown.md");
        if (!markdownFile.isFile()) markdownFile.createNewFile();
        FileService fileServiceObj = new FileService();

        fileServiceObj.appendStringToFile(markdownFile, "# Hello");
        fileServiceObj.appendStringToFile(markdownFile, "Hello Line 2");



        // expected
        File htmlDirectory = new File("dist");

        File html = new File("dist", "markdown.html");

        when(markdownConverter.convertMarkdownDirectoryToHtml(any())).thenCallRealMethod();
        when(markdownConverter.convertMarkdownToHtmlFile(any())).thenReturn(html);
        when(fileService.splitFileName(any())).thenCallRealMethod();
        when(markdownParser.mapTextToMarkdownTap(any())).thenCallRealMethod();
        when(fileParser.retrieveTextInSections(any())).thenCallRealMethod();
        when(markdownConverter.copyTemplateFileToNewHtml()).thenCallRealMethod();
        doCallRealMethod().when(fileService).appendStringToFile(any(), any());
        when(markdownToHtmlSyntaxConverter.headerToContentMap(any())).thenCallRealMethod();
        doCallRealMethod().when(fileService).replaceStringInFile(any(), any());
        markdownConverterService.convertAllBlogPostToHtml();


        assertThat(markdownDirectory.exists());
        assertThat(html).exists();
        assertThat(htmlDirectory).exists();
    }


}
