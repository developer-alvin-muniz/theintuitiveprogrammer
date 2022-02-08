package com.alvinmuniz.blog.converter.Parser;

import com.alvinmuniz.blog.converter.TagType;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class MarkdownParser {

    /***
     * Takes in Markdown file such as
     * ###Hello
     * My name is Alvin. This is a new post
     * ##First thing is first
     * How do I come here?
     * #First steps.
     * @param sectionsOfText
     * @return
     *
     * A map of a top down representation of the dom
     *
     * ###  First String will be the heading
     *      Followed by the body.
     *      Continue until the next string begins with Markdown Character
     * ##   H2
     *      Repeat above throughout document
     *
     * Hierarchy is determine from top down status
     *
     */
    public Map<TagType, List<String>> mapTextToMarkdownTap(List<String> sectionsOfText) {
        Map<TagType, List<String>> mapTextToType = new HashMap<>();
        String firstSymbol = sectionsOfText.get(0).split(" ")[0];
        List<String> copyForSection = new ArrayList<>();
        try {

            if (isMarkdownSymbol(firstSymbol)) {
                TagType symbolType = obtainTagType(firstSymbol);
                for (String line : sectionsOfText) {

                    if (this.isHeaderSymbol(firstSymbol)) {
                        line = removeMarkdownHeading(line);
                    }
                    copyForSection.add(line);
                }
                mapTextToType.put(symbolType, copyForSection);
            } else {
                throw new MarkdownFormatException("Invalid formatting in heading " +
                        "of markdown file");
            }

        } catch (MarkdownFormatException e) {
            System.out.println("Heading should be a # symbol");
        }

        return mapTextToType;
    }

    private boolean isMarkdownSymbol(String firstSymbol) {
        switch (firstSymbol) {
            case "#":
            case "##":
            case "###":
            case "####":
                return true;
            default:
                return false;
        }
    }

    private boolean isHeaderSymbol(String firstSymbol) {
        switch (firstSymbol) {
            case "#":
            case "##":
            case "###":
            case "####":
                return true;
            default:
                return false;
        }
    }


    private TagType obtainTagType(String markdownSymbol) {

        switch (markdownSymbol) {
            case "#":
                return TagType.H1;
            case "##":
                return TagType.H2;
            case "###":
                return TagType.H3;
            case "####":
                return TagType.H4;
        }

        return TagType.P;
    }

    public String removeMarkdownHeading(String s) {
        Optional<String[]> splitString = Optional.ofNullable(s.split(" "));

        if(splitString.isPresent()) {
            if(splitString.get().length > 1) {
                if (isHeaderSymbol(splitString.get()[0])) {
                    s = s.substring(1,s.length());
                    return s.trim();
                }
            }
        }

        return s;

    }
}
