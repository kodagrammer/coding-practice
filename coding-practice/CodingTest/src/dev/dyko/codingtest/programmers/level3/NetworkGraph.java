package dev.dyko.codingtest.programmers.level3;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 네트워크 (https://school.programmers.co.kr/learn/courses/30/lessons/43162)
 *      네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다.
 *      예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때
 *      컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다.
 *      따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.
 *
 *      컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때,
 *      네트워크의 개수를 return 하도록 solution 함수를 작성하시오.
 *  제약사항:
 *    - 컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
 *    - 각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
 *    - i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
 *    - computer[i][i]는 항상 1입니다.
 */
public class NetworkGraph {
    public static void main(String[] args) {
        NetworkGraph graph = new NetworkGraph();

        int n1 = 3;
        int[][] computers1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int output1 = 2;
        int result1 = graph.solution(n1, computers1);
        Assertions.assertEquals(output1, result1);

        int n2 = 3;
        int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int output2 = 1;
        int result2 = graph.solution2(n2, computers2);
        Assertions.assertEquals(output2, result2);

        int n3 = 5;
        int[][] computers3 = {{1, 0, 1, 0, 0}, {0, 1, 0, 1, 1}, {1, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 1, 0, 0, 1}};
        int output3 = 2;
        int result3 = graph.solution2(n3, computers3);
        Assertions.assertEquals(output3, result3);

        int n4 = 5;
        int[][] computers4 = {{1, 0, 1, 1, 0}, {0, 1, 0, 1, 1}, {1, 0, 1, 0, 0}, {1, 1, 0, 1, 0}, {0, 1, 0, 0, 1}};
        int output4 = 1;
        int result4 = graph.solution2(n4, computers4);
        Assertions.assertEquals(output4, result4);
    }

    // DFS
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];

        int cnt = 0;

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(computers, visited, i);
                cnt++;
            }
        }

        return cnt;
    }

    private void dfs(int[][] computers, boolean[] visited, int curr) {
        visited[curr] = true;
        // 현재 컴퓨터와 연결된 모든 컴퓨터 탐색
        for(int next = 0; next < computers.length; next++) {
            // 자기 자신이 아니고, 연결되어 있고, 아직 방문 안 했으면 재귀호출
            if(computers[curr][next] == 1 && !visited[next]) {
                dfs(computers, visited, next);
            }
        }
    }

    // BFS --> N > 10,000 일 때 재귀는 ㅑㄴStackOverflow 위험
    public int solution2(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int cnt = 0;

        // 모든 컴퓨터를 순회하며 네트워크 개수 카운트
        for(int i = 0; i < n; i++) {    // O(N)
            if(!visited[i]) {
                bfs(computers, visited, i);
                cnt++;
            }
        }

        return cnt;
    }

    private void bfs(int[][] computers, boolean[] visited, int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int next = 0; next < computers[curr].length; next++) {    // O(N)
                if(!visited[next] && computers[curr][next] == 1) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }
}
