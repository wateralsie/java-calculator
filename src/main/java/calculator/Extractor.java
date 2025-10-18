package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extractor {

    private static final String DEFAULT_DELIMS = ",:";
    // 커스텀 구분자 글자 길이 2 이상
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
            // 추출한 결과가 숫자가 아닐 때
            if (!token.matches("[0-9]+")) throw new IllegalArgumentException("에러 3");
            numbers.add(Integer.parseInt(token));
        }
        return numbers;
    }

    private void parse(String str) {
        int lastLineBreak = str.lastIndexOf("\n") + 1;
        // 원래라면 커스텀 구분자가 없어야함
        if (lastLineBreak == 0) {
            // 숫자와 기본 구분자로만 이루어져 있지 않으면
            // 유효하지 않은 구분자 존재 or 커스텀 구분자 지정 양식 잘못됨 or 유효하지 않은 구분자만 있을 때
            if (!str.matches("[0-9,:]+")) throw new IllegalArgumentException("에러 1");
            delims = DEFAULT_DELIMS;
            numbersString = str;
        } else {
            delims = DEFAULT_DELIMS + extractCustomDelimiter(str.substring(0, lastLineBreak));
            numbersString = str.substring(lastLineBreak);
        }
    }

    private String extractCustomDelimiter(String str) {
        // 커스텀 구분자 문자열 뒷부분에 지정
        // 커스텀 구분자가 숫자, -
        if (Pattern.compile("[0-9-]").matcher(str).find()) throw new IllegalArgumentException("에러 2");

        StringBuilder sb = new StringBuilder();
        Matcher matcher = CUSTOM_DELIM_PATTERN.matcher(str);
        while (matcher.find()) {
            sb.append(matcher.group(1));
        }
        return sb.toString();
    }

}
