package com.alvinmuniz.blog.converter;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


class MarkdownConverterTest {

    @InjectMocks
    MarkdownConverter markdownConverter;

    @Mock
    FileService fileService;

    @BeforeEach
    void setUp() throws IOException {
        initMocks(this);
    }

    @Test
    void convertMarkDownFileToHtml() throws IOException {

        File markdownFile = new File("directory", "markdown0.md");
        assertTrue(markdownFile.isFile());

        MarkdownConverter markdownConverter = new MarkdownConverter(fileService);

        File expected = new File("dist", "markdown0" +
                ".html");

        when(fileService.splitFileName(any())).thenCallRealMethod();

        File actual = markdownConverter.convertMarkdownToHtmlFile(markdownFile);

        assertEquals(actual.getName(), expected.getName());

        markdownFile.delete();
        actual.delete();
        expected.delete();

    }

    @Test
    void transerFileContents() throws IOException {

        //Given

        File givenDirectory = new File("directory");
        if(!givenDirectory.isDirectory()) givenDirectory.mkdir();
        File giver = new File("directory", "markdown0.md");
        if (!giver.isFile()) giver.createNewFile();
        File receiver = new File("directory", "markdown0.html");


        // function that allows me to write to a file with a string
        // write to file with a string function
        this.appendStringToFile(giver, "File Writer Test");

        if (!receiver.isFile()) {
            receiver.createNewFile();
        }
        String expected, actual;
        MarkdownConverter markdownConverter = new MarkdownConverter(fileService);

        expected = extractTextFromFile(giver);

        when(fileService.transferFileContents(any(),any())).thenCallRealMethod();

        File actualFile = fileService.transferFileContents(giver,
                receiver);

        //TODO extracted to a file
        try (BufferedReader br = new BufferedReader(
                new FileReader(actualFile, StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            actual = sb.toString();

            actualFile.delete();
            giver.delete();

        }


        assertEquals(expected, actual);

        giver.delete();
        receiver.delete();
        actualFile.delete();
    }

    private String extractTextFromFile(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file,
                StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        }
    }

    private void appendStringToFile(File file, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.append("File Writer Test");
        fileWriter.close();
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

        MarkdownConverter markdownConverter = new MarkdownConverter(fileService);
        File actual = markdownConverter.createDistFolder();

        File mkdir = new File("directory");

        assertEquals(actual.getName(), "dist");
        assertTrue(actual.isDirectory());
    }


//    @Test
    @DisplayName("takes all files in markdown directory and saves them as html")
    void convertMarkdownDirectoryToHtml() throws IOException {
        MarkdownConverter markdownConverter = new MarkdownConverter(fileService);

        // given directory
        File directory = new File("directory");
        directory.mkdirs();
        List<File> fileArray = new ArrayList<File>();
        IntStream.range(0, 10).forEach(integer -> fileArray.add(new File(
                "directory",
                integer + ".md")));

        fileArray.stream().forEach(file -> {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        File givenDirectory = new File("directory");
        if (!givenDirectory.isDirectory()) {
            givenDirectory.mkdirs();
        }

        when(fileService.splitFileName(any())).thenCallRealMethod();

        File actual =
                markdownConverter.convertMarkdownDirectoryToHtml(new File(
                        "directory"));

        assertEquals(actual.getName(), "dist");

        assertEquals(Objects.requireNonNull(actual.listFiles()).length - 8, 10);

        List<File> notHtml =
                Arrays.stream(Objects.requireNonNull(actual.listFiles())).filter(fileName -> !(fileName.getName().split("\\.")[1].equals("html"))).collect(Collectors.toList());

        Arrays.stream(Objects.requireNonNull(givenDirectory.listFiles())).forEach(File::delete);
        Arrays.stream(Objects.requireNonNull(actual.listFiles())).forEach(File::delete);

        fileArray.stream().filter(File::delete);

        assertEquals(notHtml.size(), 0);


    }

}
