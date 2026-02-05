package dev.dyko.codingtest.programmers.challenge;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 완전범죄(https://school.programmers.co.kr/learn/courses/30/lessons/389480)
 * 제약사항:
 *  - 1 ≤ info의 길이 ≤ 40
 *      - info[i]는 물건 i를 훔칠 때 생기는 흔적의 개수를 나타내며, [A에 대한 흔적 개수, B에 대한 흔적 개수]의 형태입니다.
 *      - 1 ≤ 흔적 개수 ≤ 3
 *  - 1 ≤ n ≤ 120
 *  - 1 ≤ m ≤ 120
 */
public class PerfectCrime {
    public static void main(String[] args) {
        PerfectCrime perfectCrime = new PerfectCrime();

        int[][] info1 = {{1, 2}, {2, 3}, {2, 1}};
        int n1 = 4;
        int m1 = 4;
        int output1 = 2;
        Assertions.assertEquals(output1, perfectCrime.solution2(info1, n1, m1));

        int[][] info2 = {{1, 2}, {2, 3}, {2, 1}};
        int n2 = 1;
        int m2 = 7;
        int output2 = 0;
        Assertions.assertEquals(output2, perfectCrime.solution2(info2, n2, m2));

        int[][] info3 = {{3, 3}, {3, 3}};
        int n3 = 7;
        int m3 = 1;
        int output3 = 6;
        Assertions.assertEquals(output3, perfectCrime.solution2(info3, n3, m3));

        int[][] info4 = {{3, 3}, {3, 3}};
        int n4 = 6;
        int m4 = 1;
        int output4 = -1;
        Assertions.assertEquals(output4, perfectCrime.solution2(info4, n4, m4));
    }

    // BFS : O(2^info.length) = 2^40 --> 메모리 초과
    public int solution(int[][] info, int n, int m) {
        int mininum = Integer.MAX_VALUE;

        Queue<int[]> queue = new ArrayDeque<>();    // [nextInfo, A count, B count]
        queue.offer(new int[]{0, 0, 0});

        // 각 물건마다 2가지 선택의 경우를 모두 추적 : O(2^info.length) = 2^40
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int index = curr[0];

            // 경찰에게 잡히지 않고 모두 훔친 경우, A의 최솟값 비교
            if (index == info.length) {
                mininum = Math.min(mininum, curr[1]);
                continue;
            }

            // 이미 A의 최솟값을 넘은 경로라면 탐색 중단
            if (curr[1] >= mininum) {
                continue;
            }

            int a = info[index][0];
            int b = info[index][1];

            // 추가로 훔칠 때, 걸리지 않는다면 다음 물건 절도 업무 큐에 추가
            if(a + curr[1] < n) {
                queue.offer(new int[]{index + 1, curr[1] + a, curr[2]});
            }
            if(b + curr[2] < m) {
                queue.offer(new int[]{index + 1, curr[1], curr[2] + b});
            }
        }

        return mininum == Integer.MAX_VALUE ? -1 : mininum;
    }

    // DP --> O(info.length * N * M) + O(N * M) = 40 * 120 * 120 + 120 * 120 = 약 59만
    public int solution2(int[][] info, int n, int m) {
        // 현재까지 훔친 흔적의 정보
        boolean[][] curr = new boolean[n][m];
        curr[0][0] = true;

        // 물건을 훔칠 때 마다, 누적 흔적 확인 : O(info.length)
        for(int[] item : info) {
            boolean[][] next = new boolean[n][m];

            int a = item[0];
            int b = item[1];

            // 전체를 순회하며, 현재까지의 흔적 확인 : O(N*M)
            for(int ca = 0; ca < n; ca++) {
                for(int cb = 0; cb < m; cb++) {
                    // 흔적이 있고, 경찰에 걸리지 않는다면 다음 흔적으로 등록
                    if(curr[ca][cb]) {
                        if(ca + a < n) next[ca + a][cb] = true;
                        if(cb + b < m) next[ca][cb + b] = true;
                    }
                }
            }

            // 흔적 정보 최신화
            curr = next;
        }

        for(int a = 0; a < curr.length; a++) {
            for(int b = 0; b < curr[a].length; b++) {
                if(curr[a][b]) {
                    return a;
                }
            }
        }

        return -1;
    }
}
