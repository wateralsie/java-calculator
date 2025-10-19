package calculator;

import java.util.ArrayList;
import java.util.List;
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

    public List<Integer> parseNumbers() {
        StringTokenizer tokens = new StringTokenizer(numbersString, delims);
        List<Integer> numbers = new ArrayList<>();
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            if (!token.matches("[0-9]+")) throw new IllegalArgumentException("에러 3");
            numbers.add(Integer.parseInt(token));
        }
        return numbers;
    }

    private void parse(String str) {
        int lastLineBreak = str.lastIndexOf("\n") + 1;
        if (lastLineBreak == 0) {
            if (!str.matches("[0-9,:]+")) throw new IllegalArgumentException("에러 1");
            delims = DEFAULT_DELIMS;
            numbersString = str;
        } else {
            delims = DEFAULT_DELIMS + extractCustomDelimiter(str.substring(0, lastLineBreak));
            numbersString = str.substring(lastLineBreak);
        }
    }

    private String extractCustomDelimiter(String str) {
        if (Pattern.compile("[0-9-]").matcher(str).find()) throw new IllegalArgumentException("에러 2");

        StringBuilder sb = new StringBuilder();
        Matcher matcher = CUSTOM_DELIM_PATTERN.matcher(str);
        while (matcher.find()) {
            sb.append(matcher.group(1));
        }
        return sb.toString();
    }

}
