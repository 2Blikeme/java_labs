package ru.bstu.it191.chernih.lab4.regex;

public class HTMLParser {

    private static final String REGEX = "";

    public String[] parse(String string) {
        var tagsList = string.split(REGEX);


        return tagsList;
    }
}
