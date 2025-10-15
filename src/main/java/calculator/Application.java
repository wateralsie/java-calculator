package calculator;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
    }

    public StringTokenizer parseNumbers(String input) {
        return new StringTokenizer(input, ",:");
    }
}
