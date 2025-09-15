package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.Stack;

/**
 * 문제: 괄호 회전하기
 *      다음 규칙을 지키는 문자열을 올바른 괄호 문자열이라고 정의합니다.
 *        - “( )”, “[ ]”, “{ }”는 모두 올바른 괄호 문자열입니다.
 *        - 만약 A가 올바른 괄호 문자열이라면, “(A)”, “[A]”, “{A}”도 올바른 괄호 문자열입니다.
 *          예를 들어 “[ ]”가 올바른 괄호 문자열이므로, “( [ ] )”도 올바른 괄호 문자열입니다.
 *        - 만약 A, B가 올바른 괄호 문자열이라면, AB도 올바른 괄호 문자열입니다.
 *          예를 들어 “{ }”와 “( [ ] )”가 올바른 괄호 문자열이므로, “{ } ( [ ] )”도 올바른 괄호 문자열입니다.
 *      대괄호, 중괄호, 그리고 소괄호로 이루어진 문자열 s가 매개변수로 주어집니다.
 *      이 s를 왼쪽으로 x (0 ≤ x < (s의 길이)) 칸만큼 회전시켰을 때 s가 올바른 괄호 문자열이 되게 하는 x의 개수를 반환하는 solution( ) 함수를 완성하세요.
 * 제약사항:
 *   - s의 길이는 1 이상 1,000 이하입니다.
 */
public class StackExample1 {
    public static int solution(String s) {
        // 시간복잡도: O(N^2)
        // N - s의 길이

        int result = 0;

        // x칸 씩 회전 : O(N)
        for(int x = 0; x < s.length(); x++) {
            if(isValidString(s, x)){    // 회전한 문자열 확인 : O(N)
                result++;
            }
        }

        return result;
    }

    public static boolean isValidString(String str, int x) {
        int n = str.length();
        Stack<Character> remaining = new Stack<>();

        for (int i = 0; i < n; i++) {
            char c = str.charAt((x + i) % n);   // 회전된 문자열의 i번째 문자
            if(c == '(' || c == '{' || c == '[') {
                remaining.push(c);
            } else {
                if(remaining.isEmpty()) { return false; }   // 닫힘 기호가 나왔는데 열림기호가 안남아있으면 괄호 성립불가
                if(!isMatchOpenClose(remaining.pop(), c)) {
                    return false;  // 닫힘 기호가 나왔는데 열림기호가 일치하지 않으면 괄호 성립불가, 일치하면 다음 괄호 확인
                }
            }
        }
        return remaining.isEmpty();
    }

    public static boolean isMatchOpenClose(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '{' && close == '}')
            || (open == '[' && close == ']');
    }

    public static void main(String[] args) {
        String input1 = "(){}[]";
        int output1 = 3;
        int result1 = solution(input1);
        Assertions.assertEquals(output1, result1);

        String input2 = "]}(){[";
        int output2 = 2;
        int result2 = solution(input2);
        Assertions.assertEquals(output2, result2);

        String input3 = ")({}}";
        int output3 = 0;
        int result3 = solution(input3);
        Assertions.assertEquals(output3, result3);
    }
}
