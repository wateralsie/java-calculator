package calculator;

import java.util.Scanner;

public class Input {

    private String promptMessage = "덧셈할 문자열을 입력해 주세요.";
    Scanner sc = new Scanner(System.in);
    private String userInput;

    public String getString() {
        System.out.println(promptMessage);
        userInput = sc.nextLine();
        return convertToLineBreak(userInput);
    }

    // todo: string util로 변환 가능해보임
    public String convertToLineBreak(String str) {
        return str.replace("\\n", "\n");
    }
}
