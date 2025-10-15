package calculator;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Application {
    public static void main(String[] args) {
        Application app = new Application();
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        int result = app.addNumbers(app.parseNumbers(userInput));
        System.out.println("결과 : " + result);
    }

    public StringTokenizer parseNumbers(String input) {
        return new StringTokenizer(input, ",:");
    }

    public int addNumbers(StringTokenizer st) {
        int total = 0;
        while (st.hasMoreElements()) {
            total += Integer.parseInt(st.nextToken());
        }
        return total;
    }
}
