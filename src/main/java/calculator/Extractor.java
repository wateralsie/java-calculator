package calculator;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {

    private static final String DEFAULT_DELIMS = ",:";
    private static final Pattern CUSTOM_DELIM_PATTERN = Pattern.compile("//(.)\\R");
    private String delims;
    private String numbersString;

    public Extractor(String str) {
        parse(str);
    }

    public StringTokenizer parseNumbers() {
        return new StringTokenizer(numbersString, delims);
    }

    private void parse(String str) {
        int lastLineBreak = str.lastIndexOf("\n") + 1;
        if (lastLineBreak == 0) {
            delims = DEFAULT_DELIMS;
            numbersString = str;
        } else {
            delims = DEFAULT_DELIMS + extractCustomDelimiter(str.substring(0, lastLineBreak));
            numbersString = str.substring(lastLineBreak);
        }
    }

    private String extractCustomDelimiter(String input) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = CUSTOM_DELIM_PATTERN.matcher(input);
        while (matcher.find()) {
            sb.append(matcher.group(1));
        }
        return sb.toString();
    }

}
