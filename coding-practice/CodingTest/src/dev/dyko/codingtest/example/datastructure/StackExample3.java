package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.Stack;

/**
 * 문제: 주식 가격
 *      초 단위로 기록된 주식 가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 반환하도록 solution( ) 함수를 완성하세요.
 * 제약사항:
 *   - prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
 *   - prices의 길이는 2 이상 100,000 이하입니다.
 */
public class StackExample3 {
    public static int[] solution(int[] prices) {
        // 시간복잡도: O(N)
        // N: 가격 배열의 길이
        // 일반적으로 이중루프로 비교하는 방식은 O(N^2) 가용범위 넘어감

        int n = prices.length;
        int[] result = new int[n];

        // 이전 가격과 현재 가격 비교하여, 가격이 중간에 떨어지지 않은 경우 보관
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(int i = 1; i < n; i++) {
            // 이전의 주식 가격에 대해, 현재 주식 가격보다 떨어진 경우 기간 확정하고 스택에서 제외
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int j = stack.pop();
                result[j] = i - j;
            }
            stack.push(i);
        }

        // 스택에 남아있는 경우, 끝까지 주식이 떨어지지 않은 것 --> 총 배열의 시간(n-1)에서 해당 주식의 시간(i)까지의 기간 계산
        while(!stack.isEmpty()) {
            int i = stack.pop();
            result[i] = n - 1 - i;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] prices1 = {1, 2, 3, 2, 3};
        int[] output1 = {4, 3, 1, 1, 0};
        int[] result1 = solution(prices1);
        Assertions.assertArrayEquals(output1, result1);

        int[] prices2 = {2, 5, 3, 6, 4, 1};
        int[] output2 = {5, 1, 3, 1, 1, 0};
        int[] result2 = solution(prices2);
        Assertions.assertArrayEquals(output2, result2);

        int[] prices3 = {8, 2, 1, 4, 3, 3, 5, 9};
        int[] output3 = {1, 1, 5, 1, 3, 2, 1, 0};
        int[] result3 = solution(prices3);
        Assertions.assertArrayEquals(output3, result3);
    }
}
