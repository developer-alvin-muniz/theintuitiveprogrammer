package com.alvinmuniz.blog.converter;

import com.alvinmuniz.blog.converter.Parser.FileParser;
import com.alvinmuniz.blog.converter.Parser.MarkdownParser;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;

/**
 *
 * */

@Component
public class MarkdownConverter  {

    private static FileService fileService;
    private static final File[] oldFiles = null;

    public MarkdownConverter(FileService fileService) {
        this.fileService = fileService;
    }

    public static void main(String[] args) throws IOException {

        // template file and then per markdown file we essential
        // copy the template file and replace it with the specific markdown
        // file information
//            createDistFolder();
//
//            File directory = new File("directory");
//            if(!directory.isDirectory()) directory.mkdir();
//            convertMarkdownDirectoryToHtml(directory);
//
//            File[] htmlFiles = directory.listFiles();
//
//            for(File htmlFile : htmlFiles) {
//                copyTemplateFileToNewHtml();
//
//                MarkdownParser
//                        .mapTextToMarkdownTap(
//                                FileParser.retrieveTextInSections(htmlFile));
//
//            }
//

        // generate the converted html string from markdown file
            // then replace that string with the <template></template> in the
            // markdown file


    }

    public File copyTemplateFileToNewHtml() {
        File templateFile = new File("template", "template.html");
        File copiedFile = new File("template", "copy.html");
        try {
            if(!copiedFile.isFile()) copiedFile.createNewFile();
            FileCopyUtils.copy(templateFile, copiedFile);
            return copiedFile;
        } catch (IOException exception) {
            return copiedFile;
        }
    }

    public File createDistFolder() {
        File outputDirectory = new File("dist");

        if (!outputDirectory.isDirectory()) {
            outputDirectory.mkdirs();
        }

        return outputDirectory;
    }


    public File convertMarkdownToHtmlFile(File markdownFile) throws IOException {

        String splitName = fileService.splitFileName(markdownFile);
        File htmlFile = new File("directory",
                splitName+".html");
        htmlFile.createNewFile();
        return htmlFile;
    }

    public File convertMarkdownDirectoryToHtml(File directory) throws IOException {

        File distFolder = new File("dist");

        if(!distFolder.isDirectory()) distFolder.mkdir();

        // markdown files
        for(File file : directory.listFiles()) {
            // now we have the html file
            File convertedFile = convertMarkdownToHtmlFile(file);
            // move the html file to dist
            // todo delete source file
            File source = new File("directory",file.getName());
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
