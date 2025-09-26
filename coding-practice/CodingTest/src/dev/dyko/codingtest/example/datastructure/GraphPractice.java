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
        testDfs();
        testBfs();
        testDijkstra();
    }

    private static void testDfs(){
        GraphPractice graphPractice = new GraphPractice();

        // 깊이우선탐색
        int[][] graph1 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        int start1 = 1;
        int n1 = 5;
        int[] output1 = {1, 2, 3, 4, 5};
        int[] result1 = graphPractice.dfsSolution(graph1, start1, n1);
        Assertions.assertArrayEquals(output1, result1);

        int[][] graph2 = {{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {5, 6}};
        int start2 = 1;
        int n2 = 6;
        int[] output2 = {1, 2, 4, 5, 6, 3};
        int[] result2 = graphPractice.dfsSolution(graph2, start2, n2);
        Assertions.assertArrayEquals(output2, result2);
    }

    private static void testBfs(){
        GraphPractice graphPractice = new GraphPractice();

        // 너비우선탐색
        int[][] graph1 = {{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}, {4, 8}, {5, 8}, {6, 9}};
        int start1 = 1;
        int n1 = 9;
        int[] output1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] result1 = graphPractice.bfsSolution(graph1, start1, n1);
        Assertions.assertArrayEquals(output1, result1);

        int[][] graph2 = {{1, 3}, {3, 4}, {3, 5}, {5, 2}};
        int start2 = 1;
        int n2 = 5;
        int[] output2 = {1, 3, 4, 5, 2};
        int[] result2 = graphPractice.bfsSolution(graph2, start2, n2);
        Assertions.assertArrayEquals(output2, result2);
    }

    private static void testDijkstra(){
        GraphPractice graphPractice = new GraphPractice();

        // 다익스트라 알고리즘
        int[][] graph1 = {{0,1,9}, {0,2,3}, {1,0,5}, {2,1,1}};
        int start1 = 0;
        int n1 = 3;
        int[] output1 = {0, 4, 3};
        int[] result1 = graphPractice.dijkstraSolution(graph1, start1, n1);
        Assertions.assertArrayEquals(output1, result1);

        int[][] graph2 = {{0,1,1}, {1,2,5}, {2,3,1}};
        int start2 = 0;
        int n2 = 4;
        int[] output2 = {0, 1, 6, 7};
        int[] result2 = graphPractice.dijkstraSolution(graph2, start2, n2);
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
    private ArrayList<Integer>[] _nodeInfoDfs;
    private boolean[] _visitedDfs;

    public int[] dfsSolution(int[][] graph, int start, int n){
        // 시간복잡도 : O(E + N)
        // O(E + N)
        setDfsNodeInfo(graph, n);

        ArrayList<Integer> nodes = new ArrayList<>();
        dfs(nodes, start); // DFS로 모든 노드(N)와 간선(E)을 한 번씩만 처리 → O(N + E)

        return nodes.stream().mapToInt(i -> i).toArray();   // O(N)
    }

    private void setDfsNodeInfo(int[][] graph, int n){
        // 간선 수를 E, 노드 수를 N이라고 하면 O(E + N)
        // (간선 삽입 O(E), 방문 배열 초기화 O(N))
        _nodeInfoDfs = new ArrayList[n + 1];    // 0번 인덱스 미사용
        _visitedDfs = new boolean[n + 1];

        for(int i = 1; i < n + 1; i++){
            _nodeInfoDfs[i] = new ArrayList<>();
            _visitedDfs[i] = false;
        }

        for (int[] node : graph) {
            _nodeInfoDfs[node[0]].add(node[1]);
        }
    }

    private void dfs(ArrayList<Integer> nodes, int node){
        nodes.add(node);
        _visitedDfs[node] = true;

        for(int next : _nodeInfoDfs[node]) {
            if(!_visitedDfs[next]) { // 이미 방문했다면 제외
                dfs(nodes, next);
            }
        }
    }

    /**
     * 문제: 너비 우선 탐색 순회
     *      너비 운석 탐색으로 모든 노드를 순회하는 함수 solution()을 작성하세요.
     *      시작 노드는 매개변수 start로 주어집니다. graph는 (출발노드, 도착노드) 쌍들이 들어있는 리스트입니다.
     *      반환값은 그래프의 시작 노드부터 모든 노드를 너비 우선 탐색으로 진행한 순서대로 노드가 저장된 리스트 입니다.
     * 제약사항:
     *   - 노드의 최대 개수를 100개입니다.
     *   - 시작 노드부터 시작해서 모든 노드를 방문할 수 있는 경로가 항상 있습니다.
     *   - 그래프의 노드는 숫자입니다.
     * 입출력 예:
     *   - 입력: [[1,2], [1,3], [2,4], [2,5], [3,6], [3,7], [4,8], [5,8], [6,9], [7,9]] / 1 / 9
     *   - 출력: [1,2,3,4,5,6,7,8,9]
     */
    private ArrayList<Integer>[] _nodeInfoBfs;
    private boolean[] _visitedBfs;

    public int[] bfsSolution(int[][] graph, int start, int n){
        // O(N + E)
        setBfsNodeInfo(graph, n);

        // O(N + E)
        List<Integer> answer = bfs(start);

        // O(N)
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private void setBfsNodeInfo(int[][] graph, int n){
        _nodeInfoBfs = new ArrayList[n + 1];
        _visitedBfs = new boolean[n + 1];

        for(int i = 1; i < n + 1; i++){
            _nodeInfoBfs[i] = new ArrayList<>();
            _visitedBfs[i] = false;
        }

        for(int[] node : graph) {
            _nodeInfoBfs[node[0]].add(node[1]);
        }
    }

    private ArrayList<Integer> bfs(int node){
        ArrayList<Integer> nodes = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // 루트노드 순회
        queue.add(node);
        _visitedBfs[node] = true;

        // 자식 노드들 순회하며 같은 레벨 먼저 순회, 그의 자식노드는 큐에 추가
        while(!queue.isEmpty()){
            int curr = queue.poll();
            nodes.add(curr);

            for(int next : _nodeInfoBfs[curr]) {
                if(!_visitedBfs[next]) {
                    queue.add(next);
                    _visitedBfs[next] = true;
                }
            }
        }

        return nodes;
    }

    /**
     * 문제: 다익스트라 알고리즘
     *      주어진 그래프와 시작노드를 이용하여 다익스트라 알고리즘을 구현하는 solution() 함수를 작성하세요.
     *      인수는 graph, start 총 2개 입니다. graph는 배열로 주어지며 노드의 연결 정보와 가중치가 저장되어 있습니다.
     *      예를 들어, [1, 3, 2] 일 때, 1번 노드에서 3번 노드는 가중치 2로 연결되어 있는 것입니다.
     *      start는 정수형으로 주어지며 출발노드를 의미합니다. n은 정수형으로 주어지며 노드의 개수입니다.
     *      반환값은 시작노드부터 시작노드를 포함한 모든 노드까지의 최단 거리를 순서대로 저장한 배열입니다.
     * 제약사항:
     *   - 그래프의 노드 개수는 최대 10,000개 입니다.
     *   - 각 노드는 0 이상의 10,000 이하 정수로 표현합니다.
     *   - 모든 가중치는 0 이상의 정수이며 10,000을 넘지 않습니다.
     */
    public int[] dijkstraSolution(int[][] graph, int start, int n){
        // 그래프 정보 초기화
        ArrayList<Node>[] adjList = new ArrayList[n];
        for(int i = 0; i < n; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int[] node : graph) {
            adjList[node[0]].add(new Node(node[1], node[2]));
        }

        // 모든 노드의 거리값 초기화
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 시작노드 가중치 초기화
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(start, 0)); // 시작노드 삽입

        while(!pq.isEmpty()){
            Node curr = pq.poll();

            // 현재 등록된 거리값이 더 작으면 무시
            if(dist[curr.dest] < curr.cost){
                continue;
            }

            // 현재 노드와 인접한 노드들의 거리값 계산
            for(Node next : adjList[curr.dest]) {
                // 기존에 발견했던 거리보다 더 짧은 거리를 발견하면 거리값 갱신후 큐에 추가
                if(dist[next.dest] > curr.cost + next.cost) {
                    dist[next.dest] = curr.cost + next.cost;
                    pq.add(new Node(next.dest, dist[next.dest]));
                }
            }
        }

        return dist;
    }

    private class Node{
        int dest, cost;

        public Node(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
    }
}
