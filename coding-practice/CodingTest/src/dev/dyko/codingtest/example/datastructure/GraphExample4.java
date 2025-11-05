package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * 문제: 배달 (https://school.programmers.co.kr/learn/courses/30/lessons/12978)
 * 제약사항:
 *  - 1 <= n <= 50
 *  - 1 <= road.length <= 2,000
 *    - road[i] = [a, b, c] , a,b 는 노드 정보(a!=b), c는 가중치(1 <= c <= 10,000)
 *  - 1 <= k <= 500,000
 *  - 1번 마을에서 시간 내 배달 가능한 마을 개수 return
 */
public class GraphExample4 {

    public int solution(int n, int[][] road, int k){
        // 그래프 셋팅
        List<ArrayList<int[]>> graph = new ArrayList<>();
        for(int i = 0; i < n + 1; i++) {    // 마을이 1번부터 시작, 가독성을 위해 0번 인덱스는 빈 데이터로 유지
            graph.add(new ArrayList<>());
        }

        for(int[] r : road) {
            int a = r[0];
            int b = r[1];

            graph.get(a).add(new int[]{b, r[2]});
            graph.get(b).add(new int[]{a, r[2]});
        }

        // 다익스트라 알고리즘
        int[] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);   // 모든 노드의 거리 값을 최대로 지정

        Queue<int[]> queue = new PriorityQueue<>((o1, o2)->Integer.compare(o1[1], o2[1]));
        queue.add(new int[]{1, 0});
        dists[1] = 0;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int now = curr[0];
            int dist = curr[1];

            // 이전 경로의 최단거리를 갱신하지 못했으면 다음 경로 진행X
            if(dists[now] < dist) { continue; }

            // 다음 경로의 최단거리 갱신 후 큐에 추가
            for(int[] r : graph.get(now)) {
                int next = r[0];
                if(dists[next] > dist + r[1]) {
                    dists[next] = dist + r[1];
                    queue.add(new int[]{next, dist + r[1]});
                }
            }
        }

        int cnt = 0;
        for(int dist : dists) {
            if(dist <= k) cnt++;
        }

        return cnt;
    }

    public static void main(String[] args) {
        GraphExample4 graphExample = new GraphExample4();

        int n1 = 5;
        int[][] road1 = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int k1 = 3;
        int output1 = 4;
        int result1 = graphExample.solution(n1, road1, k1);
        Assertions.assertEquals(output1, result1);

        int n2 = 6;
        int[][] road2 = {{1,2,1},{1,3,2},{2,3,2},{3,4,3},{3,5,2},{3,5,3},{5,6,1}};
        int k2 = 4;
        int output2 = 4;
        int result2 = graphExample.solution(n2, road2, k2);
        Assertions.assertEquals(output2, result2);
    }
}
