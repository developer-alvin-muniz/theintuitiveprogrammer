package com.alvinmuniz.blog.converter;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class MarkdownConverter  {


    public MarkdownConverter() {
    }

    public static void main(String[] args) throws IOException {

        MarkdownConverter markdownConverter = new MarkdownConverter();



    }

    public static File createDistFolder() {
        File outputDirectory = new File("dist");

        if (!outputDirectory.isDirectory()) {
            outputDirectory.mkdirs();
        }
        return outputDirectory;
    }


    public File convertMarkdownToHtmlFile(File markdownFile) throws IOException {
        String splitName = this.splitFileName(markdownFile);
        File htmlFile = new File("directory",
                splitName+".html");
        htmlFile.createNewFile();
        return htmlFile;
    }

    public File transferFileContents(File source, File intake) throws IOException {
//        FileInputStream in = new FileInputStream(intake);
//        FileOutputStream out = new FileOutputStream(source);
        FileCopyUtils.copy(source, intake);

        return intake;

    }



    private String splitFileName(File markdownFile) {
        String[] result = markdownFile.getName().split("\\.");
        Arrays.stream(result).forEach(word -> System.out.println(word));
     return  result[0];
    }


    public File converMarkdownDirectoryToHtml(File directory) throws IOException {

        File distFolder = new File("dist");

        // markdown files
        for(File file : directory.listFiles()) {
            // now we have the html file
            File convertedFile = this.convertMarkdownToHtmlFile(file);
            // move the html file to dist
            File source = new File("directory",convertedFile.getName());
            File dest = new File("dist",convertedFile.getName());
            dest.createNewFile();
            try {
                FileCopyUtils.copy(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return distFolder;
    }
}
