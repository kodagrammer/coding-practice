package dev.dyko.codingtest.programmers.level2;

import org.junit.jupiter.api.Assertions;

/**
 * 문제: 타겟 넘버 (https://school.programmers.co.kr/learn/courses/30/lessons/43165)
 * 제약사항:
 *   - 주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
 *   - 각 숫자는 1 이상 50 이하인 자연수입니다.
 *   - 타겟 넘버는 1 이상 1000 이하인 자연수입니다.
 */
public class TargetNumber {
    public static void main(String[] args) {
        TargetNumber targetNumber = new TargetNumber();

        int[] numbers1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        int output1 = 5;
        Assertions.assertEquals(output1, targetNumber.solution(numbers1, target1));

        int[] numbers2 = {4, 1, 2, 1};
        int target2 = 4;
        int output2 = 2;
        Assertions.assertEquals(output2, targetNumber.solution(numbers2, target2));
    }

    // 완전탐색 (DFS) : O(2^N)
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    private int dfs(int[] numbers, int target, int idx, int sum) {
        // 종료지점
        if (idx == numbers.length) {
            return sum == target ? 1 : 0;
        }

        // 플러스, 마이너스 경우의 수를 계산
        int plus = dfs(numbers, target, idx + 1, sum + numbers[idx]);
        int minus = dfs(numbers, target, idx + 1, sum - numbers[idx]);

        return plus + minus;
    }
}
