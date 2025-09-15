package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.Stack;

/**
 * 문제: 짝지어 제거하기
 *      알파벳 소문자로 구성된 문자열에서 같은 알파벳이 2개 붙어 있는 짝을 찾습니다. 짝을 찾은 다음에는 그 둘을 제거한 뒤 앞뒤로 문자열을 이어붙입니다.
 *      이 과정을 반복해서 문자열을 모두 제거한다면 짝지어 제거하기가 종료됩니다. 문자열 S가 주어졌을 때 짝지어 제거하기를 성공적으로 수행할 수 있는지 반환하는 함수를 완성하세요.
 *      성공적으로 수행할 수 있으면 1을, 아니면 0을 반환해주면 됩니다. 예를 들어 문자열 S가 baabaa라면
 *        - baabaa → bbaa → aa
 *      순서로 문자열을 모두 제거할 수 있으므로 1을 반환합니다.
 * 제약사항:
 *   - 문자열의 길이 : 1,000,000 이하의 자연수
 *   - 문자열은 모두 소문자로 이루어져 있습니다.
 */
public class StackExample2 {
    public static int solution(String str) {
        // 시간복잡도: O(N) * O(1) = O(N)
        // N: 문자열 str의 길이

        // 제거되지 않은 문자 저장
        Stack<Character> stack = new Stack<>();

        // 문자열 순회하여 문자 저장/제거
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if(!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }

    public static void main(String[] args) {
        String input1 = "baabaa";
        int output1 = 1;
        int result1 = solution(input1);
        Assertions.assertEquals(output1, result1);

        String input2 = "cdcd";
        int output2 = 0;
        int result2 = solution(input2);
        Assertions.assertEquals(output2, result2);

        String input3 = "addcabbaca";
        int output3 = 1;
        int result3 = solution(input3);
        Assertions.assertEquals(output3, result3);
    }
}
