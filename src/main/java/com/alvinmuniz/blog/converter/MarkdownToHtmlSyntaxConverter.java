package com.alvinmuniz.blog.converter;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MarkdownToHtmlSyntaxConverter {
    public MarkdownToHtmlSyntaxConverter() {
    }

    public String headerToContentMap(Map<TagType, List<String>> headerToContentMap) {
       // only one header tag
        for(TagType headerTag: headerToContentMap.keySet()){
            String openingTag = obtainOpeningTagFromType(headerTag);
            String closingTag = obtainClosingTagFromType(headerTag);
            String openingParagraphTag = "<P>";
            String closingParagraphTag = "</P>";
            StringBuilder sb = new StringBuilder();

            String header = headerToContentMap.get(headerTag).get(0);

            sb.append(openingTag + header + closingTag);

            List<String> lineValues = headerToContentMap.get(headerTag)
                    .stream().filter(line -> !line.equals(header)).collect(Collectors.toList());
            sb.append(openingParagraphTag);
            for(String line : lineValues){
                sb.append(line);
            };
            sb.append(closingParagraphTag);

            return sb.toString();

        };
        return "";
    }

    private String obtainClosingTagFromType(TagType headerTag) {

        switch (headerTag) {
            case H1:
                return "</H1>";
            default:
                return "";
        }
    }

    private String obtainOpeningTagFromType(TagType headerTag) {
        switch (headerTag) {
            case H1:
                return "<aH1>";
            default:
                return "";
        }
    }
}
