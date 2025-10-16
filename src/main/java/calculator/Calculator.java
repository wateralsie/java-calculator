package calculator;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public void run() {
        Input input = new Input();
        String userInput = input.read();
        Extractor extractor = new Extractor(userInput);
        StringTokenizer numberList = extractor.parseNumbers();
        int result = add(numberList);
        System.out.println("결과 : " + result);
    }

    private int add(StringTokenizer st) {
        int total = 0;
        while (st.hasMoreElements()) {
            total += Integer.parseInt(st.nextToken());
        }
        return total;
    }
}
