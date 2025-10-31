package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 문제: 네트워크
 *      컴퓨터간 상호간 정보 교환을 위한 네트워크 연결이 되어 있다.
 *      컴퓨터 A,B가 네트워크 연결이 되어 있고 컴퓨터 B,C가 연결되어 있을 때, 컴퓨터 A,C는 간접 연결되어 정보를 교환할 수 있다.
 *      컴퓨터 개수가 N, 연결정보가 담긴 2차원 배열 computers가 주어질 때, 네트워크 개수를 반환하는 solution 함수를 구하라
 * 제약사항:
 *   - 1 <= n <= 200
 *   - n의 인덱스는 0부터 시작
 *   - i,j 컴퓨터가 연결되어 있을 시, computers[i][j] = 1 로 표현
 *   - computers[i][i]는 항상 1
 * 입출력 예:
 *   - n=3, computers=[[1,1,0], [1,1,0], [0,0,1]], result=2
 */
public class GraphExample2 {
    public int solution(int n, int[][] computers) {
        // 경로 탐색 정보 및 네트워크 저장
        boolean[] visited = new boolean[n];
        int result = 0;

        // 0번 컴퓨터부터 연결되어 있는 네트워크 확인
        // 시간복잡도 : O(N^2)
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(visited, i, computers);
                result++;
            }
        }

        return result;
    }

    private void dfs(boolean[] visited, int computerIdx, int[][] computers) {
        visited[computerIdx] = true;
        for (int j : computers[computerIdx]) {  // 인접노드 탐색 --> 최악의 경우 O(N)
            // 네트워크 연결이 되어 있고, 접근한 적 없는 컴퓨터면 탐색
            if (computers[computerIdx][j] == 1 && !visited[j]) {
                dfs(visited, j, computers);
            }
        }
    }

    public static void main(String[] args) {
        GraphExample2 graphExample = new GraphExample2();

        int n1 = 3;
        int[][] computers1 = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int output1 = 2;
        int result1 = graphExample.solution(n1, computers1);
        Assertions.assertEquals(output1, result1);

        int n2 = 3;
        int[][] computers2 = new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        int output2 = 1;
        int result2 = graphExample.solution(n2, computers2);
        Assertions.assertEquals(output2, result2);

        int n3 = 5;
        int[][] computers3 = new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 0, 1}};
        int output3 = 3;
        int result3 = graphExample.solution(n3, computers3);
        Assertions.assertEquals(output3, result3);
    }
}