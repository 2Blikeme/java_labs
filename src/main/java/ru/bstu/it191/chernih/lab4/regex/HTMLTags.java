package ru.bstu.it191.chernih.lab4.regex;

public class HTMLTags {

    public static final String SPAN = "<span %s>%s</span>";

    public static final String LINK = "<a %s>%s</a>";

    public static final String IMG = "<img %s/>";

    public static class Properties {

        public static final String STYLE = "style=\"%s\"";

        public static final String HREF = "href=\"%s\"";

        public static final String SRC = "src=\"%s\"";
    }
}
