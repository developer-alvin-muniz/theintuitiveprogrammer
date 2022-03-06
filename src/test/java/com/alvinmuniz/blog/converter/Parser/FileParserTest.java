package com.alvinmuniz.blog.converter.Parser;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileParserTest {

//    @Test
    void retrieveParsedFile() throws IOException {

        File given = new File("test", "file.txt");
        File givenDirectory = new File("test");
        if(!givenDirectory.isDirectory())  givenDirectory.mkdir();
        if(!given.isFile()) given.createNewFile();
        this.appendStringToFile(given, "### String to File");
        this.appendStringToFile(given, "String to File 2");

        FileParser fileParser = new FileParser();

        List<String> result = fileParser.retrieveTextInSections(given);

//        verify(fileParser.startReadingFromFile(given));
        List<String> expected = Arrays.asList("### String to File", "String " +
                "to File 2");

        assertEquals(result.size(), expected.size());
        for (int i = 0, expectedLength = expected.size(); i < expectedLength; i++) {

            assertEquals(result.get(i), expected.get(i));
        }

        given.delete();
        givenDirectory.delete();
    }

    private void appendStringToFile(File file, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.append(text);
        fileWriter.append(System.lineSeparator());
        fileWriter.close();
    }

}
