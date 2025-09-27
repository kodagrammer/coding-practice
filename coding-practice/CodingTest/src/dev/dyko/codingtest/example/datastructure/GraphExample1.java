package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 게임 맵 최단 거리 (https://school.programmers.co.kr/learn/courses/30/lessons/1844)
 *      게임 맵의 상태 maps가 매개변수로 주어질 때, 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값을 return 하도록 solution 함수를 완성해주세요.
 *      단, 상대 팀 진영에 도착할 수 없을 때는 -1을 return 해주세요.
 * 제약사항:
 *   - maps는 n x m 크기의 게임 맵의 상태가 들어있는 2차원 배열로, n과 m은 각각 1 이상 100 이하의 자연수입니다.
 *   - n과 m은 서로 같을 수도, 다를 수도 있지만, n과 m이 모두 1인 경우는 입력으로 주어지지 않습니다.
 *   - maps는 0과 1로만 이루어져 있으며, 0은 벽이 있는 자리, 1은 벽이 없는 자리를 나타냅니다.
 *   - 처음에 캐릭터는 게임 맵의 좌측 상단인 (1, 1) 위치에 있으며, 상대방 진영은 게임 맵의 우측 하단인 (n, m) 위치에 있습니다.
 */
public class GraphExample1 {
    // 이동할 수 있는 방향의 배열
    private final int[] rx = {0, 0, 1, -1};
    private final int[] ry = {1, -1, 0, 0};

    public int solution(int[][] maps){
        // 시간복잡도 : O(NM)
        // 맵의 크기
        int n = maps.length;
        int m = maps[0].length;

        int[][] dist = new int[n][m];

        // BFS 탐색
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0)); // 시작노드(캐릭터 위치) 등록
        dist[0][0] = 1;

        while(!queue.isEmpty()){
            Node now = queue.poll();

            // 이동할 수 있는 모든 방향(사방)
            for(int i = 0; i < 4; i++) {
                int nr = now.r + rx[i];
                int nc = now.c + ry[i];

                // 맵 밖으로 나가는 경우
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                // 벽으로 막혀있는 경우
                if(maps[nr][nc] == 0) continue;

                // 이동한 위치가 처음 접근하는 좌표인 경우, 큐와 거리 추가
                if(dist[nr][nc] == 0) {
                    queue.add(new Node(nr, nc));
                    dist[nr][nc] = dist[now.r][now.c] + 1;
                }
            }
        }

        // 목적지에 도달하지 못한 경우, -1 반환
        return dist[n-1][m-1] == 0 ? -1 : dist[n-1][m-1];
    }

    private class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        GraphExample1 graphExample1 = new GraphExample1();

        int[][] maps1 = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        int output1 = 11;
        int result1 = graphExample1.solution(maps1);
        Assertions.assertEquals(output1, result1);

        int[][] maps2 = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
        int output2 = -1;
        int result2 = graphExample1.solution(maps2);
        Assertions.assertEquals(output2, result2);
    }
}
