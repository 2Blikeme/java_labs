package ru.bstu.it191.chernih.lab4.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class HTMLParser {

    private static final String TAGS_REGEX = "\\[(.*?)]";
    private static final String INNER_TEXT_REGEX = "](.*?)\\[";
    private static final String TAG_VALUE_REGEX = "(\\\"[#\\w\\s|(https?:\\/\\/(?:www\\.|" +
            "(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+" +
            "[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\." +
            "[a-zA-Z0-9]+\\.[^\\s]{2,})\n"
            + "]+|\\\")";
    private static final String TAG_REGEX = "([^\"|=]*)";

    private String getTagValue(String string) {
        Pattern p = Pattern.compile(TAG_REGEX);
        var m = p.matcher(string);
        return m.find() ? m.group(1) : "";
    }


    private List<String> getByRegex(String string, String regex) {
        Pattern p = Pattern.compile(regex);
        var m = p.matcher(string);
        List<String> list = new ArrayList<>();
        while (m.find()) {
            list.add(m.group(1));
        }
        return list;
    }


    public String parse(String string) {
        var tags = getByRegex(string, TAGS_REGEX);
        var values = getByRegex(string, INNER_TEXT_REGEX);
        values.add("");

        var resultString = new StringBuilder();
        int textValueIndex = 0;
        for (String s : tags) {
            var tag = getTagValue(s);
            if (tag.contains("/")) {
                resultString.append(HTMLTags.SPAN_END_TAG).append(values.get(textValueIndex));
            } else {
                var tagTemplate = HTMLTags.tagsMap.get(tag);
                var res = String.format(tagTemplate,
                        HTMLTags.styleMap.get(tag), getByRegex(s, TAG_VALUE_REGEX).get(0)
                );
                resultString.append(res);
                resultString.append(values.get(textValueIndex));
            }
            textValueIndex++;
        }
        return resultString.toString();
    }
}
