package calculator;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().replace("\\n", "\n");

        String customDelims = app.addCustomDelimiter(userInput.replace("\\n", "\n"));
        String[] dividedUserInput = userInput.split("\\R");
        int result = app.addNumbers(app.parseNumbers(dividedUserInput[dividedUserInput.length - 1], customDelims));

        System.out.println("결과 : " + result);
    }

    public StringTokenizer parseNumbers(String input) {
        return new StringTokenizer(input, ",:");
    }

    public StringTokenizer parseNumbers(String input, String customDelim) {
        String delims = ",:" + customDelim;
        return new StringTokenizer(input, delims);
    }

    public int addNumbers(StringTokenizer st) {
        int total = 0;
        while (st.hasMoreElements()) {
            total += Integer.parseInt(st.nextToken());
        }
        return total;
    }

    public String addCustomDelimiter(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern customDelimiterPattern = Pattern.compile("//(.)\\R");
        Matcher matcher = customDelimiterPattern.matcher(input);
        while (matcher.find()) {
            sb.append(matcher.group(1));
        }
        return sb.toString();
    }
}
