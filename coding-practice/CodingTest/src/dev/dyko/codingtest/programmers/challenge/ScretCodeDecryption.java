package dev.dyko.codingtest.programmers.challenge;

import org.junit.jupiter.api.Assertions;

/**
 * 문제: 비밀코드해독 (https://school.programmers.co.kr/learn/courses/30/lessons/388352)
 * 제한사항:
 *   - 10 ≤ n ≤ 30
 *   - 1 ≤ (q의 길이 = m) ≤ 10
 *     - q[i]의 길이 = 5
 *     - q[i]는 i+1번째 시도에서 입력한 5개의 서로 다른 정수를 담고 있으며, 오름차순으로 정렬되어 있습니다.
 *     - 1 ≤ q[i][j] ≤ n
 *   - ans의 길이 = m
 *      - ans[i]는 i+1번째 시도에서 입력한 5개의 정수 중 비밀 코드에 포함된 정수의 개수를 나타냅니다.
 *      - 0 ≤ ans[i] ≤ 5
 *   - 비밀 코드가 존재하지 않는(답이 0인) 경우는 주어지지 않습니다.
 */
public class ScretCodeDecryption {
    private int n;
    private int[][] q;
    private int[] ans;
    private int result;

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        this.result = 0;

        // 1~n까지 숫자 중 5개 선택
        backtrack(0, 0, 1);
        return result;
    }

    // picked: 현재 조합(숫자 리스트), cnt: 선택 숫자 수, start: 다음 숫자
    private void backtrack(int pickedMask, int cnt, int start) {
        if (cnt == 5) {
            if (isValid(pickedMask)) result++;
            return;
        }
        for (int i = start; i <= n; i++) {
            backtrack(pickedMask | (1 << (i - 1)), cnt + 1, i + 1);
        }
    }

    // 모든 시도와 ans[i] 조건 만족 여부 확인
    private boolean isValid(int pickedMask) {
        for (int i = 0; i < q.length; i++) {
            int count = 0;
            for (int num : q[i]) {
                if ((pickedMask & (1 << (num - 1))) != 0) { // 교집합 계산
                    count++;
                }
            }
            if (count != ans[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ScretCodeDecryption scretCodeDecryption = new ScretCodeDecryption();

        int n1 = 10;
        int[][] q1 = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {3, 7, 8, 9, 10}, {2, 5, 7, 9, 10}, {3, 4, 5, 6, 7}};
        int[] ans1 = {2, 3, 4, 3, 3};
        int output1 = 3;
        int result1 = scretCodeDecryption.solution(n1, q1, ans1);
        Assertions.assertEquals(output1, result1);

        int n2 = 15;
        int[][] q2 = {{2, 3, 9, 12, 13}, {1, 4, 6, 7, 9}, {1, 2, 8, 10, 12}, {6, 7, 11, 13, 15}, {1, 4, 10, 11, 14}};
        int[] ans2 = {2, 1, 3, 0, 1};
        int output2 = 5;
        int result2 = scretCodeDecryption.solution(n2, q2, ans2);
        Assertions.assertEquals(output2, result2);
    }
}
