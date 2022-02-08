package com.alvinmuniz.blog.converter;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Service
public class FileService {

    public  String splitFileName(File markdownFile) {
        return  markdownFile.getName().split("\\.")[0].toString();
    }

    public  File transferFileContents(File source, File intake) throws IOException {
        FileCopyUtils.copy(source, intake);
        return intake;
    }

    public void appendStringToFile(File file, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.append(text);
        fileWriter.append(System.lineSeparator());
        fileWriter.close();
    }

    public void replaceStringInFile(File file, String newContent) {


        try {
            Path path = Paths.get(file.getPath());
            Charset charset = StandardCharsets.UTF_8;
            String content = new String(Files.readAllBytes(path), charset);
            content = content.replaceAll("<template></template>", newContent);
            Files.write(path, content.getBytes(charset));
        } catch (Exception fileException) {
        }

    }


}
