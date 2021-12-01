package com.alvinmuniz.blog.converter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class MarkdownConverterTest {

    @BeforeAll
    static void setUp() throws IOException {

        File directory = new File("directory");
        directory.mkdirs();
        List<File> fileArray = new ArrayList<File>();

        for (int i = 0; i < 10; i++) {
            File file = new File(directory.getName(), "markdown" + i + ".md");
            fileArray.add(file);
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append("Hello World");
            fileWriter.close();
        }

        fileArray.stream().forEach(file -> {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void convertMarkDownFileToHtml() throws IOException {

        File markdownFile = new File("directory", "markdown0.md");
        assertTrue(markdownFile.isFile());

        MarkdownConverter markdownConverter = new MarkdownConverter();

        File expected = new File("dist", "markdown0" +
                ".html");

        File actual = markdownConverter.convertMarkdownToHtmlFile(markdownFile);

        assertEquals(actual.getName(), expected.getName());

    }

    @Test
    void transerFileContents() throws IOException {

        //Given
        File giver = new File("directory", "markdown0.md");
        if (!giver.isFile()) {
            giver.createNewFile();
        }
        File receiver = new File("directory", "markdown0.html");
        if (!receiver.isFile()) {
            receiver.createNewFile();
        }
        String expected, actual;
        MarkdownConverter markdownConverter = new MarkdownConverter();

        try (BufferedReader br = new BufferedReader(
                new FileReader(giver, StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
            expected = sb.toString();
        }


        File actualFile = markdownConverter.transferFileContents(giver,
                receiver);

        try (BufferedReader br = new BufferedReader(
                new FileReader(actualFile, StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            actual = sb.toString();

        }
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Convert Markdown Directory to HTML")
    void createDistFolder() throws IOException {
        //Given a file in the markdown directory
        File giver = new File("directory", "markdown0.md");
        if (!giver.isFile()) {
            giver.createNewFile();
        }

        File receiver = new File("directory", "markdown0.html");
        if (!receiver.isFile()) {
            receiver.createNewFile();
        }

        MarkdownConverter markdownConverter = new MarkdownConverter();
        File actual = markdownConverter.createDistFolder();

        File mkdir = new File("directory");

        assertEquals(actual.getName(), "dist");
        assertTrue(actual.isDirectory());
    }

    @Test
    @DisplayName("takes all files in markdown directory and saves them as html")
    void convertMarkdownDirectoryToHtml() throws IOException {
        MarkdownConverter markdownConverter = new MarkdownConverter();
        File givenDirectory = new File("directory");
        if (!givenDirectory.isDirectory()) {
            givenDirectory.mkdirs();
        }
        File actual =
                markdownConverter.converMarkdownDirectoryToHtml(new File(
                        "directory"));
        assertEquals(actual.getName(), "dist");
        assertEquals(actual.listFiles().length, 10);


        List<File> notHtml =
                Arrays.stream(actual.listFiles()).filter(fileName -> !(fileName.getName().split("\\.")[1].equals("html"))).collect(Collectors.toList());

        // delete the html files after
        Arrays.stream(givenDirectory.listFiles()).filter(fileName -> (fileName.getName().split("\\.")[1].equals("html"))).forEach( file -> file.delete());

        assertEquals(notHtml.size(), 0);
    }


}
