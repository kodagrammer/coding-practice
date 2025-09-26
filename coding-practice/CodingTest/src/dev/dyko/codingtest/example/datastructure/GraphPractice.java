package dev.dyko.codingtest.example.datastructure;

import org.junit.jupiter.api.Assertions;

import java.util.*;

public class GraphPractice {
    /*
     * 그래프 개념
     *   - 노드(데이터)와 간선(데이터간 관계/흐름)을 이용항 비선형 구조
     *   - 구현: 인접행렬 or 인접리스트
     * 그래프 탐색
     *   - 깊이우선탐색: 가능한 모든 해 찾기
     *   - 너비우선탐색: 최단경로 찾기, 네트워크 분석 문제
     * 그래프 최단경로 구하기
     *   - 다익스트라 알고리즘
     *      - 가중치가 있는 그래프의 최단 경로 구하기
     *      - 현재까지 발견한 가장 적은 비용으로 갈 수 있는 노드를 경유해서, 각 노드까지의 최단 경로 갱신 작업을 반복
     *      - 음의 가중치가 있는 경우, 비효율적
     *   - 벨만-포드 알고리즘
     *      - 매 단계마다 모든 간선의 가중치를 다시 확인하여 최소 비용을 갱신
     *      - 음의 가중치가 있어도 최단 경로 도출 가능, 음의 순환 감지 가능
     */
    public static void main(String[] args) {
        GraphPractice graphPractice = new GraphPractice();

        int[][] graph1 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        int start1 = 1;
        int n1 = 5;
        int[] output1 = {1, 2, 3, 4, 5};
        int[] result1 = graphPractice.solution(graph1, start1, n1);
        Assertions.assertArrayEquals(output1, result1);

        int[][] graph2 = {{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {5, 6}};
        int start2 = 1;
        int n2 = 6;
        int[] output2 = {1, 2, 4, 5, 6, 3};
        int[] result2 = graphPractice.solution(graph2, start2, n2);
        Assertions.assertArrayEquals(output2, result2);
    }

    /**
     * 문제: 깊이 우선 탐색 순회
     *      깊이 우선 탐색으로 모든 그래프의 노드를 순회하는 함수 solution()을 작성하세요.
     *      시작노드는 start로 주어집니다. 그래프는 [출발노드, 도착노드] 쌍들이 들어있는 리스트입니다.
     *      반환값은 그래프의 시작 노드부터 모든 깊이를 깊이 우선 탐색으로 진행한 순서대로 노드가 저장된 리스트입니다.
     * 제약조건:
     *   - 노드의 최대개수는 100을 넘지 않습니다.
     *   - 시작 노드부터 시작해서 모든 노드를 방문할 수 있는 경로가 항상 있습니다.
     *   - 그래프의 모든 노드는 문자열 입니다.
     * 입출력 예:
     *   - 입력: [[1, 2], [2, 3], [3, 4], [4, 5]] / 1 / 5
     *   - 출력: [1, 2, 3, 4, 5]
     */
    // DFS에서는 인접 리스트를 List로 두고 for문으로 순회하는 게 더 직관적이고 재사용성이 좋음
    // Queue를 쓰면 한 번 DFS를 돌고 나면 그래프 정보가 파괴되기 때문에 한 번만 탐색 가능
    private ArrayList<Integer>[] _nodeInfo;
    private boolean[] _visited;

    public int[] solution(int[][] graph, int start, int n){
        // 시간복잡도 : O(E + N)
        // O(E + N)
        setNodeInfo(graph, n);

        ArrayList<Integer> nodes = new ArrayList<>();
        searchGraph(nodes, start); // DFS로 모든 노드(N)와 간선(E)을 한 번씩만 처리 → O(N + E)

        return nodes.stream().mapToInt(i -> i).toArray();   // O(N)
    }

    private void setNodeInfo(int[][] graph, int n){
        // 간선 수를 E, 노드 수를 N이라고 하면 O(E + N)
        // (간선 삽입 O(E), 방문 배열 초기화 O(N))
        _nodeInfo = new ArrayList[n + 1];    // 0번 인덱스 미사용
        _visited = new boolean[n + 1];

        for(int i = 1; i < n + 1; i++){
            _nodeInfo[i] = new ArrayList<>();
            _visited[i] = false;
        }

        for (int[] node : graph) {
            _nodeInfo[node[0]].add(node[1]);
        }
    }

    private void searchGraph(ArrayList<Integer> nodes, int node){
        nodes.add(node);
        _visited[node] = true;

        for(int next : _nodeInfo[node]) {
            if(!_visited[next]) { // 이미 방문했다면 제외
                searchGraph(nodes, next);
            }
        }
    }
}
