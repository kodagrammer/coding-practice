package dev.dyko.codingtest.programmers.challenge;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 서버 증설 횟수 (https://school.programmers.co.kr/learn/courses/30/lessons/389479)
 * 제약사항:
 *   - players의 길이 = 24
 *      - 0 ≤ players의 원소 ≤ 1,000
 *      - players[i]는 i시 ~ i+1시 사이의 게임 이용자의 수를 나타냅니다.
 *   - 1 ≤ m ≤ 1,000
 *   - 1 ≤ k ≤ 24
 */
public class ServerExpansion {
    public static void main(String[] args) {
        ServerExpansion serverExpansion = new ServerExpansion();

        int[] players1 = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m1 = 3;
        int k1 = 5;
        int output1 = 7;
        Assertions.assertEquals(output1, serverExpansion.solution(players1, m1, k1));

        int[] players2 = {0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0};
        int m2 = 5;
        int k2 = 1;
        int output2 = 11;
        Assertions.assertEquals(output2, serverExpansion.solution(players2, m2, k2));

        int[] players3 = {0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1};
        int m3 = 1;
        int k3 = 1;
        int output3 = 12;
        Assertions.assertEquals(output3, serverExpansion.solution(players3, m3, k3));
    }

    public int solution(int[] players, int m, int k) {
        Map<Integer, Integer> serverMap = new HashMap<>();  // <서버 만료시간, 증설 대수>
        int answer = 0;
        int current = 0; // 현재까지 증설되어 있는 서버수

        // 시간복잡도 : O(players.length) --> O(24)
        for (int time = 0; time < players.length; time++) {
            // 만료된 서버 제거
            Integer expireServer = serverMap.remove(time);
            if(expireServer != null) current -= expireServer;

            int need = players[time]/m;    // 필요한 서버수
            int expansion = need - current;

            // 서버수가 부족하면 추가
            if(expansion > 0) {
                int expireTime = time + k;
                serverMap.put(expireTime, serverMap.getOrDefault(expireTime, 0) + expansion);
                current += expansion;
                answer += expansion;
            }
        }

        return answer;
    }
}
