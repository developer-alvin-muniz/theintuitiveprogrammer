package com.alvinmuniz.blog.converter.Parser;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class FileParser {

    public List<String> retrieveTextInSections(File file) {
        String stringFromFile = extractTextAsStringFromFile(file);
        String[] splitStringIntoSections =
                splitStringOnLineBreak(stringFromFile);

        return Arrays.asList(splitStringIntoSections);
    }

    private String[] splitStringOnLineBreak(String stringFromFile) {
        String sbSanitised = stringFromFile.replace("\n", "4xcr-20982");
        return sbSanitised.split("4xcr-20982");
    }

    private String extractTextAsStringFromFile(File given) {

        if (!given.isDirectory()) {
            try (BufferedReader br = new BufferedReader(new FileReader(given,
                    StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }

                while(sb.indexOf("\n") != -1) {
                    int index = sb.indexOf("\n");
                    sb.replace(index,index+1,"^%<br>");
                }
                while(sb.indexOf("^%") != -1) {
                    int index = sb.indexOf("^%");
                    sb.replace(index,index+2,"\n");
                }


                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return "";
    }
}
