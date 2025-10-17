package calculator;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public void run() {
        String userInput = new Input().read();
        int result;
        // 빈 값이거나 공백으로만 구성
        if (userInput == null || userInput.isBlank()) result = 0;
        // 숫자만 존재
        else if (userInput.matches("[0-9]+")) result = Integer.parseInt(userInput);
        // 유효한 구분자만 존재
        else if (userInput.matches("[,:]+")) result = 0;
        else {
            Extractor extractor = new Extractor(userInput);
            StringTokenizer numberList = extractor.parseNumbers();
            // 커스텀 구분자만 있고 숫자가 없음 (커스텀 구분자 지정 양식 + 커스텀 구분자만)
            if (numberList.countTokens() == 0) result = 0;
            else result = add(numberList);
        }
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
