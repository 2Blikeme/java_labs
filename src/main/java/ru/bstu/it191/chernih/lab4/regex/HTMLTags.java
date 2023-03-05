package ru.bstu.it191.chernih.lab4.regex;

import java.util.HashMap;
import java.util.Map;

public class HTMLTags {

    public static final String SPAN_END_TAG = "</span>";

    public static final Map<String, String> tagsMap = new HashMap<>();

    public static final Map<String, String> styleMap = new HashMap<>();

    static {
        styleMap.put("font", "font-family");
        styleMap.put("size", "font-size");
        styleMap.put("color", "color");
        styleMap.put("url", "");

        tagsMap.put("font", "<span style=\"%s: %s\">");
        tagsMap.put("size", "<span style=\"%s: %s\">");
        tagsMap.put("color", "<span style=\"%s: %s\">");
        tagsMap.put("img", "<img src=\"%s%s\"/>");
        tagsMap.put("url", "<a href=\"%s%s\">");
        tagsMap.put("/font", SPAN_END_TAG);
        tagsMap.put("/size", SPAN_END_TAG);
        tagsMap.put("/url", "</a>");
    }
}
