package com.alvinmuniz.blog.converter;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndentingAlgorithm {


    public static void main(String[] args) {

    }

    public void formatText(String text) {

        int counter = 0;

        for(int i = 0; i < text.toCharArray().length; i++) {
            char[] textArr = text.toCharArray();
            if(textArr[i] == '<') {
                // start detecting the closing tag
                // if it doesn't have a / char, it's an opening tag
                // if opening tag then start detecting the next text
            }

        }
        }



    public boolean isTag(String s) {
        char[] array = s.toCharArray();
        return array[0] == '<' && array[array.length - 1] == '>';
    }

    public boolean isOpeningTag(String s) {
        return !(s.toCharArray()[1] == '/') && this.isTag(s);
    }

    public boolean isClosingTag(String s) {
        return s.toCharArray()[1] == '/' && this.isTag(s);
    }
}

