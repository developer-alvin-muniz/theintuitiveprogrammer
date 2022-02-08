package com.alvinmuniz.blog.service;

import com.alvinmuniz.blog.converter.FileService;
import com.alvinmuniz.blog.converter.MarkdownConverter;
import com.alvinmuniz.blog.converter.MarkdownToHtmlSyntaxConverter;
import com.alvinmuniz.blog.converter.Parser.FileParser;
import com.alvinmuniz.blog.converter.Parser.MarkdownParser;
import com.alvinmuniz.blog.converter.TagType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class MarkdownConverterService {

    private final MarkdownConverter markdownConverter;
    private final FileParser fileParser;
    private final MarkdownParser markdownParser;
    private final MarkdownToHtmlSyntaxConverter markdownToHtmlSyntaxConverter;
    private final FileService fileService;

    public MarkdownConverterService(MarkdownConverter markdownConverter, FileParser fileParser, MarkdownParser markdownParser, MarkdownToHtmlSyntaxConverter markdownToHtmlSyntaxConverter, FileService fileService) {
        this.markdownConverter = markdownConverter;
        this.fileParser = fileParser;
        this.markdownParser = markdownParser;
        this.markdownToHtmlSyntaxConverter = markdownToHtmlSyntaxConverter;
        this.fileService = fileService;
    }

    public void convertAllBlogPostToHtml() throws IOException {
        final File directory = new File("directory");
        if (!directory.isDirectory()) directory.mkdir();
        try {
            File destDirectory =
                    markdownConverter.convertMarkdownDirectoryToHtml(directory);

            for (File htmlFile : destDirectory.listFiles()) {
                if(!htmlFile.isDirectory()) {
                    List<String> textToConvert =
                            fileParser.retrieveTextInSections(htmlFile);
                    Map<TagType, List<String>> mapTextToTag =
                            markdownParser.mapTextToMarkdownTap(textToConvert);

                    String finalHtmlContents =
                            markdownToHtmlSyntaxConverter.headerToContentMap(mapTextToTag);
                    if(htmlFile.exists()) htmlFile.delete();

                    File copiedFile = new File("dist", htmlFile.getName());
                    File htmlTemplate =
                            markdownConverter.copyTemplateFileToNewHtml();

                    fileService.replaceStringInFile(htmlTemplate, finalHtmlContents);
                    FileCopyUtils.copy(htmlTemplate, copiedFile);

                    if(!copiedFile.isFile()) copiedFile.createNewFile();

                }

            }
        } catch (Exception exception) {

        }

    }
}
