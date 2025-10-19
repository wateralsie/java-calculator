package calculator;

import java.util.Scanner;

public class Input {

    private static final String PROMPT_MESSAGE = "덧셈할 문자열을 입력해 주세요.";
    private final Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String read() {
        System.out.println(PROMPT_MESSAGE);
        String input = scanner.nextLine();
        return convertToLineBreak(input);
    }

    private String convertToLineBreak(String str) {
        return str.replace("\\n", "\n");
    }
}
