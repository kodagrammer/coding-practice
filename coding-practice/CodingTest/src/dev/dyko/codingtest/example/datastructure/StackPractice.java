package dev.dyko.codingtest.example.datastructure;

import org.junit.Assert;

import java.util.Stack;

public class StackPractice {
    public static void main(String[] args) {
        // 올바른 괄호 체크
        String input1 = "()()";
        boolean output1 = true;
        boolean result1 = checkString(input1);
        Assert.assertEquals(output1, result1);

        String input2 = "(()(";
        boolean output2 = false;
        boolean result2 = checkString(input2);
        Assert.assertEquals(output2, result2);

        // 10진수를 2진수로 변환
        int decimal1 = 10;
        String binaryNum1 = "1010";
        String result3 = changeDecimalToBinary(decimal1);
        Assert.assertEquals(binaryNum1, result3);

        int decimal2 = 25;
        String binaryNum2 = "11001";
        String result4 = changeDecimalToBinary(decimal2);
        Assert.assertEquals(binaryNum2, result4);

        int decimal3 = 1;
        String binaryNum3 = "1";
        String result5 = changeDecimalToBinary(decimal3);
        Assert.assertEquals(binaryNum3, result5);
    }

    /**
     * 문제: ‘(’ 또는 ‘)’ 로만 이루어진 문자열 s가 주어졌을 때, 문자열 s가 올바른 괄호이면 true를 반환하고 올바르지 않은 괄호면 false를 반환하는 solution( ) 함수를 완성하시오
     * 제약조건:
     *   - 문자열 s의 길이 : 100,000 이하의 자연수
     *   - 문자열 s는 ‘(’ 또는 ‘)’로만 이루어져 있습니다.
     * 입출력 예시:
     *   - 입: "(())()" / 출: true
     *   - 입: ")()(" / 출: false
     */
    public static boolean checkString(String s) {
        // 시간복잡도: O(N) * O(1) = O(N)
        // N - s의 길이
        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()) {
            // 열린괄호는 스택에 입력
            if('(' == c){
                stack.push(c);
            } else {
                // 닫힌괄호가 나오면 스택의 열린괄호 꺼내기 --> 스택이 비어있거나 닫힌괄호가 나오면 올바르지 않은 문자열
                if (stack.isEmpty() || c == stack.pop()) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * 문제: 10진수를 입력받아 2진수로 변환해 반환하는 solution( ) 함수를 구현하세요.
     * 제약조건:
     *   - decimal은 1이상 10억 미만의 자연수 // O(logN) 복잡도만 가용성 있음
     * 입출력 예시:
     *   - 입: 10 / 출: 1010
     *   - 입: 27 / 출: 11011
     */
    public static String changeDecimalToBinary(int decimal) {
        // 시간복잡도: O(logN) + O(logN) = O(logN)
        // N - decimal 크기
        Stack<Integer> stack = new Stack<>();

        // 10진수를 2로 나눈 나머지 값 저장
        while (decimal > 0) {
            stack.push((decimal % 2));
            decimal /= 2;
        }

        // 나머지 값을 거꾸로 읽어 2진법 완성
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}
